package socket;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String SEARCHONE = "1. 查询从北京机场出发的所有航班";
    public static final String SEARCHTWO = "2. 查询到港时间早于10:00的所有航班";
    public static final String EXIT = "3. 退出";

    private static final String hostname = "127.0.0.8";
    private static final int port = 9913;
    private static final int requestTimeout = 1000 * 60;

    private static Socket socket;
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;

//    发送
    public static void send(String msg) throws IOException {
        printWriter.println(msg);
    }

//    接收
    public static String receive() throws IOException {
        return bufferedReader.readLine();
    }

    public static void main(String[] args) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(hostname, port), requestTimeout);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("client start");
            String ss = receive();
            System.out.println("ss:" + ss);
            System.out.println("client received");
            System.out.println("请选择：");
            System.out.println(SEARCHONE);
            System.out.println(SEARCHTWO);
            System.out.println(EXIT);
            Scanner input = new Scanner(System.in);
            String msg = input.next();
            while (!msg.equals("3")) {
                send(msg);
                System.out.println(receive());
                System.out.println("请选择：");
                System.out.println(SEARCHONE);
                System.out.println(SEARCHTWO);
                System.out.println(EXIT);
                msg = input.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("再见！");
                printWriter.close();
                bufferedReader.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
