import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class BrokerServer implements Runnable {
	public static int SERVICE_PORT = 9998;
	private final Socket socket;
	public BrokerServer(Socket socket){
		this.socket = socket;
	}

	public void run(){
		try{
			BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());

			while(true){
				String str = in.readLine();
				if(str == null){
					continue;
				}
				System.out.println("接收到原始数据："+str);
				if(str.equals("CONSUME")){
					String message = Broker.consume();
					out.println(message);
					//立即刷新
					out.flush();
				}else{
					//生产消息
					Broker.produce(str);
				}
			}

		}catch(Exception e){
			/*若客户端关闭socket连接，则可能会抛出异常*/
			e.printStackTrace();
		}
	}

	public static void main(String[] args)throws Exception{
		ServerSocket server = new ServerSocket(SERVICE_PORT);
		while(true){
			BrokerServer brokerServer = new  BrokerServer(server.accept());
			new Thread(brokerServer).start();
		}
	}
}
