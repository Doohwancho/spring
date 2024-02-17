package socket.step3_nio_chat;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/*
---
Server는 아래와 같이 동작합니다.

ServerSocketChannel을 생성 후 localhost:8080 주소에 매핑
Selector 생선 후 ServerSocketChannel과 연동
무한 루프에 진입해서 다음과 같이 동작

selector.select()와 selector.selectedKeys() 메서드를 호출해서 네트워크 이벤트를 기다리고, 발생한 네트워크 이벤트를 조회
OP_ACCEPT 네트워크 이벤트가 발생하면(새로운 TCP connection 생성) 별도의 소켓(SocketChannel)을 생성. 생성된 SocketChannel은 OP_READ 이벤트를 추적할 수 있도록 selector 등록
OP_READ 네트워크 이벤트가 발생하면 이벤트와 관련된 SocketChannel을 조회. 조회된 SocketChannel을 활용해서 수신한 데이터를 처리 및 조회.




---
Client는 아래와 같이 동작합니다.

Server와 통신하기 위한 SocketChannel을 생성 후 localhost:8080에 바인딩합니다.
OP_READ 이벤트를 모니터링하기 위해 Selector을 생성해서 SocketChannel에 등록합니다.
SocketChannel은 OP_READ 이벤트가 발생하면(서버로부터 데이터가 수신되면) 수신된 데이터를 콘솔에 프린트합니다.
콘솔에 유저가 입력한 데이터를 서버로 송신합니다.



 */

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Select either one of the server or client to run the application.");
        createServer();
        // startClient();
    }
    
    static void createServer() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); // non-blocking 동작을 위해 설정
        InetSocketAddress address = new InetSocketAddress(8080); // localhost:8080
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);
        
        // serverSocketChannel은 OP_ACCEPT 네트워크 이벤트를 모니터링
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        while (true) {
            // 최소 1개 이상의 네트워크 이벤트(OP_ACCEPT, OP_READ)가 발생될때까지 blocking
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            
            // SelectionKey를 순회하면서 SelectionKey에 적합한 로직을 처리
            for (Iterator<SelectionKey> i = selectedKeys.iterator(); i.hasNext(); ) {
                SelectionKey k = i.next();
                if ((k.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) { //client에서 온 TCP 연결 요청인 경우
                    System.out.println("New TCP Connection.");
                    i.remove();
                    
                    // 새로운 TCP connection을 처리하기 위한 별도의 SocketChannel을 할당
                    ServerSocketChannel ssc = (ServerSocketChannel) k.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    // socketChannel은 OP_READ 네트워크 이벤트를 모니터링
                    sc.register(selector, SelectionKey.OP_READ);
                } else if ((k.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) { //client에서 온 READ 요청인 경우
                    i.remove();
                    
                    SocketChannel sc = (SocketChannel) k.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = sc.read(buffer);
                    
                    if (bytesRead == -1) {
                        System.out.println("TCP connection closed");
                        k.cancel();
                    } else {
                        buffer.flip();
                        byte[] content = new byte[bytesRead];
                        buffer.get(content, 0, bytesRead);
                        System.out.println("Received: \n" + new String(content, StandardCharsets.UTF_8));
                        
                        ByteBuffer message = ByteBuffer.wrap(content);
                        sc.write(message);
                    }
                }
            }
        }
    }
    
    static void startClient() throws Exception {
        // Client용 SocketChannel 생성
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080)); // localhost:8080
        socketChannel.configureBlocking(false); // non-blocking
        System.out.println("Socket connected");
        
        // Selector 생성 후 OP_READ 등록
        // selector는 event listener로써 작동한다.
        final Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        
        new Thread(() -> {
            while (true) {
                try {
                    // OP_READ 이벤트를 모니터링. 서버로부터의 데이터 수신 시 아래 로직 실행
                    selector.select();
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    for (Iterator<SelectionKey> i = selectedKeys.iterator(); i.hasNext(); ) {
                        SelectionKey k = i.next();
                        
                        if ((k.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                            i.remove();
                            SocketChannel sc = (SocketChannel) k.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int bytesRead = sc.read(buffer);
                            
                            if (bytesRead == -1) {
                                System.out.println("TCP connection closed");
                                k.cancel();
                            } else {
                                buffer.flip();
                                byte[] content = new byte[bytesRead];
                                buffer.get(content, 0, bytesRead);
                                
                                System.out.println("Received: \n" + new String(content));
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }
        }, "selector thread").start();
        
        // System.in에 입력된 메시지를 서버로 송신
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            // exit 입력 시 종료
            if (Objects.equals(line, "exit")) break;
            
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            buffer.put(line.getBytes());
            buffer.flip();
            
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
        }
        
        socketChannel.close();
        System.out.println("Socket channel closed");
    }
}