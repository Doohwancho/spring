---\
rdb modeling - 물리 모델(table ERD)

![ERD](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbQCn9o%2FbtrDYeNs1CK%2FMwz0KO8TcKFcpk2gsjkLy0%2Fimg.png)
- MEMBER: 회원 엔티티의 Address 임베디드 타입 정보가 회원 테이블에 그대로 들어갔다. 이것은 DELIVERY 테이블도 마찬가지다.
- ITEM: 앨범, 도서, 영화 타입을 통합해서 하나의 테이블로 만들었다. DTYPE 컬럼으로 타입을 구분한다.
