package socket.simpleOneOnOneConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;



public class Client {
    public static void main(String[] args) {

        Socket socket = null;

        try {
            // Server와 통신하기 위한 Socket
            socket = new Socket();
            System.out.println("\n[ Request ... ]");
            // Server 접속
            socket.connect(new InetSocketAddress("localhost", 12345));
            System.out.println("\n[ Success ... ]");

            byte[] bytes = null;
            String message = null;
            // Socket에서 가져온 출력스트림
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            // send bytes
            message = "클라이언트에서 보내는 데이터";
            bytes = message.getBytes("UTF-8");
            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);
            dos.flush();

            System.out.println("\n[ Data Send Success ]\n" + message);

            // Socket에서 가져온 입력스트림
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            // read int
            int receiveLength = dis.readInt();

            // receive bytes
            if (receiveLength > 0) {
                byte receiveByte[] = new byte[receiveLength];
                dis.readFully(receiveByte, 0, receiveLength);

                message = new String(receiveByte);
                System.out.println("\n[ Data Receive Success ]\n" + message);
            }

            // OutputStream, InputStream close
            os.close();
            is.close();

            // Socket 종료
            socket.close();
            System.out.println("\n[ Socket closed ]\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!socket.isClosed()) {
            try {
                socket.close();
                System.out.println("\n[ Socket closed ]\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
