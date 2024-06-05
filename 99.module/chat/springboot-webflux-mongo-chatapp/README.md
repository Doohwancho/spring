# how to setup?

1. install and run mongodb from here: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-os-x/
2. brew services start mongodb-community@6.0
3. mongosh
4. use chatdb
5. db.createCollection("chat");
6. db.runCommand( { convertToCapped: 'chat', size: 8192 } )
7. start springboot app
8. run frontend on 2 different server
9. talk to each other
10. brew services stop mongodb-community@6.0
11. ps aux | grep -v grep | grep mongod


# takeaway

## WebFlux

spring reactive stack
async, non-blocking io

backend server를 non-blocking io를 쓸거면 db도 non-blocking db 써야 함.
그래서 mongodb 쓴거고, 위에 설정에 4. db.runCommand( { convertToCapped: 'chat', size: 8192 } )  해준 것.



flux = 흐른다

db에 {id=1} 이 있고, user1가 db.write(id=2)를 했다고 치자.
아직 transaction 다 안끝났는데 user2가 db.read()했다고 하면,
기존 blocking io같았으면 {id=1}만 read했을텐데, non-blocking io라 드가자마자 read로 흘러감.
write끝나고 자원 반환하고 이런짓 안하는 듯.



## SSE over socket

http protocol은 stateless, connectionless해서,

client1,2가 중간에 server 끼고 채팅하고 싶어도, 상태가 없어서 끊김.
(물론 db에 유저 누가 누구한테 보냈고 이런거 기록하게 만들 순 있는데 db io 비효율)

socket은 양방향이라
client1 <-> server <-> client2 이렇게 이어져있음.
lichess.org 생각해보면 될 듯.

근데 SSE(server side event)는
client1 <- server -> client2 이렇게 단방향으로 이어져있어서, client1,2가 서버에 뭘 보내면,
pub/sub 처럼 연결된 애한테 뿌리는 형태.



특이한 점은 SSE도 socket처럼 연결이 안끊기고 유지되기 때문에,
서버에서 client에게 뭘 보내면, 클라이언트는 창을 refresh안해도 값이 자동으로 바뀜.


사용처: chat, mail, 쪽지 수 auto update시키고 싶은 경우













# reference
https://www.youtube.com/playlist?list=PL93mKxaRDidHRYNYYFr1x3mLKIx1wFeFc


