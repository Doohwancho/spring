package socket.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] arg) throws IOException {
        Socket socket = null;            //Server와 통신하기 위한 Socket
        DataInputStream in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
        BufferedReader in2 = null;        //키보드로부터 읽어들이기 위한 입력스트림

        DataOutputStream out = null;    
        
        try {

            socket = new Socket();    
            socket.connect(new InetSocketAddress("localhost", 12345)); //서버로 접속
            
            in = new DataInputStream(socket.getInputStream()); //초기 connection 이후 서버에서 온게 있으면 이걸 사용해서 출력             
            in2 = new BufferedReader(new InputStreamReader(System.in)); 
            out = new DataOutputStream(socket.getOutputStream());        

            //채팅에 사용 할 닉네임을 입력받음
            System.out.print("닉네임을 입력해주세요 : ");
            String data = in2.readLine();            
            
            //서버로 닉네임을 전송
            out.writeUTF(data);               
            //사용자가 채팅 내용을 입력 및 서버로 전송하기 위한 쓰레드 생성 및 시작
            Thread th = new Thread(new Send(out));
            th.start();
        }catch(IOException e) {
        	e.printStackTrace();
        	
        	socket.close();
        	in.close();
        	in2.close();
        	out.close();
        }
        try {
            //클라이언트의 메인 쓰레드는 서버로부터 데이터 읽어들이는 것만 반복.
            while(true)
            {
                String str2 = in.readUTF();        
                System.out.println(str2);
            }
        }catch(IOException e) {
        	e.printStackTrace();
        } finally {
        	socket.close();
        	in.close();
        	in2.close();
        	out.close();
        }
    }
}