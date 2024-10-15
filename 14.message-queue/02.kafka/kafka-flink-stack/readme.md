# Container structure

kafka broker : 3\
zookeeper : 1\
kafdrop (Web UI): 1\
flink job manager : 1\
flink task manager : 1\
flink sql client : 1



# Quick Start

1) network 생성\
$ docker network create jssvs-net

2) docker-compose up\
$ docker-compose -f docker-compose.yml up -d


# browser connection
kafdrop : http://localhost:9000 \
filnk dashboard : http://localhost:8081


# 간단 실습

카프카 binary 버전을 다운로드 받은후 하위  bin 디렉토리에 CLI 쉘 스크립트를 이용한다.

## 1) 토픽 생성
$ ./kafka-topics.sh --bootstrap-server 127.0.0.1:9091 --topic my-topic1 --create --partitions 1 --replication-factor 1

## 2) 토픽 정보 보기
$ ./kafka-topics.sh --bootstrap-server 127.0.0.1:9091 --topic my-topic1 --describe

## 3) 토픽 구독하기 (consumer)
$ ./kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9091 --from-beginning --topic my-topic1

## 4) 토픽 삭제 하기
$ ./kafka-topics.sh --bootstrap-server 127.0.0.1:9091 --topic my-topic1 --delete
