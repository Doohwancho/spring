# 선착순 쿠폰 시스템

## a. 요구사항
선착순 100명에게 할인쿠폰을 제공

- 선착순 100명에게만 제공
- 101개 이상 지급되면 안된다
- 순간적으로 몰리는 트래픽을 버틸 수 있어야 함


## b. 문제
service layer에 apply()에 @Transaction이 안붙어서 그런가 db에 count+1 해서 딱 100 맞추는게 잘 안됨.

api/test code 로 확인 가능



DB의 쿠폰 발행 개수 count 쿼리로 쿠폰 발행 개수를 조절하는 경우
```java
public void apply(Long userId) {
    long count = couponRepository.count();

    // 발급 가능 개수 초과시 발급X
    if (count > 100) {
        return;
    }

    couponRepository.save(new Coupon(userId));
}
```

## c. solution

### 1. java's lock (e.g @Synchronized)로 해결해볼까? -> 실패
1. java lock은 scale out 환경에서는 못쓴다.


### 2. db lock, java lock 쓸까? -> 비효율
2. 수만명이 한 곳에 접근해서 lock걸고 read~write 하는 구조는 lock contention 때문에 자원소모도 크고 latency도 길어진다.


### 3. redis queue 쓰자.
3. queue에 넣고 순서대로 빼는 것, 그리고 일정 수준 이상 이면 안넣는게 lock contention을 피하면서 선착순 시스템 구현하기 적합한 구조이다.


## d. what is this code?
1. api/에서 선착순 100명을 redis's queue에 넣고, 101명 부터 return Exception.
2. multi-module system이라 api/에서 선착순 100명이 producer로 kafka한테 메시지 보냄
3. multi-module에 coupon-module이 저 메시지를 구독 중, 받으면 coupon 관련 비즈니스 로직 처리 함.
	- consumer/에 구현되있고 가장 기본적인 if coupon exist, do x, else, write on failed_coupon etc 이런식으로 구현되있음
