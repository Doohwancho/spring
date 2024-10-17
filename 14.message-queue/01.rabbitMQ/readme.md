---\
Goal


familiarize with rabbitMQ concepts



---\
Concepts


a. sync vs async ver (install -> run rabbitMQ && monitoring QUI -> 1 producer, 1 consumer on spring AMQP(advanced message queuing protocol)) :white_check_mark:\
b. work queues (1 producer -> 2 consumers)\
c. pub/sub model\
d. routing\
e. topics\
f. communication protocol(format) ex. json, rpc\
g. error-handle: publisher confirm\
h. error-handle: global handler\
i. error-handle: dead-letter exchange\
j. error-handle: retries\
k. stream\
x. performance: scale out\
x. performance: load balancing\
x. msa


---\
Resources


a-1. [hello world from rabbitMQ documentation's get-started](https://www.rabbitmq.com/tutorials/tutorial-one-java) \
a-1. [hello world code example](https://github.com/spring-projects/spring-amqp-samples) \
f-1. [message format을 json으로 통신하기](https://github.dev/spring-projects/spring-amqp-samples) \
g-1. [confirm from exchange](https://github.dev/spring-projects/spring-amqp-samples) \
h-1. [global error handler](https://github.dev/spring-projects/spring-amqp-samples) \
k-1. [rabbitmq stream 100개 message 보내기 실험](https://github.dev/spring-projects/spring-amqp-samples)
