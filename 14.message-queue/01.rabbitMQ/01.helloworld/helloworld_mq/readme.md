# A. What


message queue

producer -> queue -> consumer



# B. How to run?


1. build pom.xml
2. run sync/BrokerConfigurationApplication (queue)
3. run sync/producer (producer)
4. run sync/consumer (consumer)
5. http://localhost:15672
	- id: guest
	- pw: guest

![](images/2024-10-15-20-10-24.png)

1. Publish: 1 ops/s
2. Get(auto ack): 1 ops/s


# C. Listening ports


| Protocol   | Bound to | Port  |
|------------|----------|-------|
| amqp       | 127.0.0.1| 5672  |
| clustering | ::       | 25672 |
| http       | ::       | 15672 |
| mqtt       | ::       | 1883  |
| stomp      | ::       | 61613 |
| stream     | ::       | 5552  |


# D. sync vs async

sync가
1. 구현 쉽고
2. 에러 디버깅 더 직관적이고,
3. 메시지 order 잡기 쉬움

async가
1. high throughput에 더 유리함
2. loosely connected, distributed system에 더 유리
3. network latency에 영향을 덜 받음(안기다려도 되니까)


