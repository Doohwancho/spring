package _5_socket.step1_simple_client_server_1on1_connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
진행 흐름

- server
  1. Socket에 OutputStream에 문자열을 byte[]로 담아
  2. 클라이언트와 TCP 연결 수립된 서버 소켓으로 byte[] Stream을 받는다.
  3. InputStream을 만들어 클라이언트 소켓으로부터 온 byte[]를 문자열로 바꿔서 출력한다.
  4. OutputStream에 문자열을 담아 클라이언트 소켓으로 flush() 한다.
  5. OutputStream, InputStream 스트림 자원을 반환한다.
  6. 소켓을 닫는다.

 */

public class Server extends Thread {
    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (true) {
                // Socket에서 가져온 출력스트림
                OutputStream os = this.socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                // Socket에서 가져온 입력스트림
                InputStream is = this.socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                // read int
                int recieveLength = dis.readInt();

                // receive bytes
                byte receiveByte[] = new byte[recieveLength];
                dis.readFully(receiveByte, 0, recieveLength);
                String receiveMessage = new String(receiveByte);
                System.out.println("receiveMessage : " + receiveMessage);
                System.out.println("[ Data Receive Success ]\n");

                // send bytes
                String sendMessage = "서버에서 보내는 데이터";
                byte[] sendBytes = sendMessage.getBytes("UTF-8");
                int sendLength = sendBytes.length;
                dos.writeInt(sendLength);
                dos.write(sendBytes, 0, sendLength);
                dos.flush(); //clear buffer and flush it out to out-stream 

                System.out.println("sendMessage : " + sendMessage);
                System.out.println("[ Data Send Success ]");
            }
        } catch (EOFException e) {
            // readInt()를 호출했을 때 더 이상 읽을 내용이 없으면 EOFException이 발생한다.
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.socket != null) {
                    System.out.print("\n[ Socket closed ] ");
                    System.out.println("Disconnected :" + this.socket.getInetAddress().getHostAddress() + ":"
                            + this.socket.getPort());
                    this.socket.close();
                }
            } catch (Exception e) {
            }
        }
    }
	
}
