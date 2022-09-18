package socket.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String arg[]) {
        //접속한 Client와 통신하기 위한 Socket
        Socket socket = null;    
        //채팅방에 접속해 있는 Client 관리 객체                
        User user = new User();        
        //Client 접속을 받기 위한 ServerSocket            
        ServerSocket server_socket=null;              
        
        int count = 0;                            
        Thread thread[]= new Thread[10];  //최대 접속 인원 10명       
        
        try {
            server_socket = new ServerSocket(12345);
            //Server의 메인쓰레드는 게속해서 사용자의 접속을 받음
            while(true)
            {
                socket = server_socket.accept();

                thread[count] = new Thread(new Receiver(user,socket));
                thread[count].start();
                count++;
            }
        }catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	try {
				server_socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        };
    }
}

/*


---
how to run?

1. console창 new console view로 3개 띄움. (1 for server, 1 for client1, 1 for client 2) 
2. 서버 하나 실행 후, console 1 고정
3. 클라이언트 하나 실행 후, console 2 고정  
4. 클라이언트 두번째 실행 후, console 3 고정.


---
console.log

A. server

채팅 참여 인원 : 1
hello채팅 참여 인원 : 2
채팅 참여 인원 : 1
채팅 참여 인원 : 0


B. Client 1

닉네임을 입력해주세요 : doohwan
Server:daehwan 입장하셨습니다.
daehwan:hello?
hi?
doohwan:hi?
daehwan:what is going on?
is this theory?
doohwan:is this theory?
Server:daehwan 퇴장하셨습니다.


C. Client 2

닉네임을 입력해주세요 : daehwan
hello?
daehwan:hello?
doohwan:hi?
what is going on?
daehwan:what is going on?
doohwan:is this theory?




---
structure

A. Server (12345포트로 클라이언트 받는 소켓 하나와, 유저들끼리 채팅하는 소켓 하나 따로 관리) 
	- Receiver (유저 객체 자체를 관리. 유저가 메시지를 서버로 쏘면, user.sendMsg(msg , name); 로 DataOutputStream을 통해 채팅창 전체유저와 연결된 소켓에게 보냄) 
		- User (유저 더하고 빼고 메시지 보내고) 
B. Client
	- Send (Client가 서버로 메시지 보내는거 관리) 




 */
