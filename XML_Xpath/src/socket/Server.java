package socket;
import utils.FlightSearch;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final String WELCOMEMSG = "欢迎使用机票查询服务！";

    private static class SocketServer implements Runnable {
        private Socket socket;
        private PrintWriter printWriter;
        private BufferedReader bufferedReader;

        public SocketServer(Socket socket) throws IOException {
            this.socket = socket;
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        }

//        写入
        private void send(String msg) throws IOException {
            printWriter.println(msg);
        }

//        读出
        private String receive() throws IOException {
            return bufferedReader.readLine();
        }

        private void welcomeMsg() throws IOException {
            send(WELCOMEMSG);
            System.out.println("welcome finish");
        }

        @Override
        public void run() {
            try {
                welcomeMsg();
                String input = receive();
                while(!input.equals('3')) {
                    if (input.equals('1')) {
                        send(FlightSearch.searchByLeave());
                    } else if (input.equals('2')) {
                        send(FlightSearch.searchByLeave());
                    }
                    input = receive();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                    printWriter.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9913);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new SocketServer(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
