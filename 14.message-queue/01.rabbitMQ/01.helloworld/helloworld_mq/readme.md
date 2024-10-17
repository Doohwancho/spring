# A. What
rabbitmq(message queue) experiments

- producer -> router(direct/fanout/topic/header exchange) -> queue or stream -> consumer
	1. producer
		  - producer의 메시지는 특정 topic/tag 붙일 수 있고, 특정 topic/tag에 따라 consumer가 가려받는 형태
	2. exchange
		- types(direct/fanout(roundrobin)/topic/header) 기준으로 queue/stream에게 보내는 router
	3. queue or stream
		  1. queue: message are consumed and removed, used 1 time.
		  2. stream: Messages are retained and can be replayed; used for high-throughput, event streaming.
	4. consumer
		- 메시지를 topic, tag 별로 가려받음
		- message 타입 말고 다른 프로토콜(e.g rpg, json)으로도 받을 수 있음.
- monitoring:
	- docs: https://www.rabbitmq.com/docs/monitoring
- managing:
	- error handling
		1. confirms: producer -> exchange에게 보내면 ack:ok 뜨는데, exchange->queue 못보내면 error 던져주는게 confirm. reliable publishing을 위한 exception 처리 도움
	- docs: https://www.rabbitmq.com/docs/manage-rabbitmq


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


## b-1. Listening ports

| Protocol   | Bound to | Port  |
|------------|----------|-------|
| amqp       | 127.0.0.1| 5672  |
| clustering | ::       | 25672 |
| http       | ::       | 15672 |
| mqtt       | ::       | 1883  |
| stomp      | ::       | 61613 |
| stream     | ::       | 5552  |


# C. sync vs async

sync가
1. 구현 쉽고
2. 에러 디버깅 더 직관적이고,
3. 메시지 order 잡기 쉬움

async가
1. high throughput에 더 유리함
2. loosely connected, distributed system에 더 유리
3. network latency에 영향을 덜 받음(안기다려도 되니까)


# D. router(exchange)
## d-1. types
exchange: producer에서 받은 message를 queue로 라우팅 해주는 애

1. Direct Exchange
2. Fanout Exchange
3. Topic Exchange
4. Headers Exchange

## d-2. confirms
producer -> exchange(router) 보낸거 성공하면 ack=true 뜸\
문제는, exchange -> queue 보낼 때 실패하면 producer가 알 방법이 없잖음?\
그걸 알려주는게 confirm

confirm file's three scenarios:
- case1) 메시지를 올바른 queue에 올바른 router(default exchange "")에 보내는 것
- case2) 메시지를 wrong queue에 올바른 default router(exchange)인 ""로 보내는 것
- case3) 메시지를 올바른 queue로 보내는데 exchange(router)를 이상한 곳으로 보내는 코드 (nack received)

흥미로운건 case2인데,\
producer -> exchange 까진 잘 갔으니까 ack=true 뜨는데,\
exchange -> queue 실패한걸\
confirm이 알려줌


# E. Error
메시지 에러처리 하는 custom handler 등록하는 방법

NOTE: Such a simple handler is not generally needed since the framework will log the failed message itself; for a real
application, some additional action might be taken, for example write the bad message to a database.


# F. json
queue에서 메시지를 보내거나 받을 때, format을 json type으로 할 수도 있다.

spring app 끼리 message로 통신하는게 아니라, 다른 형식의 앱과 통신할 때 쓰인다.



# G. queue vs stream

1. Queue: Messages are consumed and removed; used for one-time message delivery.
2. Stream: Messages are retained and can be replayed; used for high-throughput, event streaming.


stream built for high throughput, low latency messaging

ex. event sourcing, data pipeline, log aggregation, etc
