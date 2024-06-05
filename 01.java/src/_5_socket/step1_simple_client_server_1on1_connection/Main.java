package _5_socket.step1_simple_client_server_1on1_connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*

---
mac commandline



1. find port number of open sockets in tcp


netstat -vanp tcp | grep 3000

or

lsof -i tcp:3000



2. kill port


kill -15 <PID>



 */

public class Main {
	public static int tcpServerPort = 12345;

	public static void main(String[] args) {
	    new Main(tcpServerPort);
	}
	
	public Main(int portNo) {
	    tcpServerPort = portNo;
	    try {
	        // ServerSocket 생성
	        ServerSocket serverSocket = new ServerSocket();
	        serverSocket.bind(new InetSocketAddress(tcpServerPort));
	        System.out.println("Starting tcp Server: " + tcpServerPort);
	        System.out.println("[ Waiting ]\n");
	        while (true) {
	            // socket -> bind -> listen socket 클래스 내부에 구현되어 있음
	            Socket socket = serverSocket.accept();
	            System.out.println("Connected " + socket.getLocalPort() + " Port, From " + socket.getRemoteSocketAddress().toString() + "\n");
	            // Thread
	            Server tcpServer = new Server(socket);
	            tcpServer.start();
	        }
	    } catch (IOException io) {
	        io.getStackTrace();
	    }
	}
}
/*

---
console.log



Client.java


[ Request ... ]

[ Success ... ]

[ Data Send Success ]
클라이언트에서 보내는 데이터

[ Data Receive Success ]
서버에서 보내는 데이터

[ Socket closed ]




Server.java


Starting tcp Server: 12345
[ Waiting ]

Connected 12345 Port, From /127.0.0.1:56578

receiveMessage : 클라이언트에서 보내는 데이터
[ Data Receive Success ]

sendMessage : 서버에서 보내는 데이터
[ Data Send Success ]

[ Socket closed ] Disconnected :127.0.0.1:56578




오?!






*/