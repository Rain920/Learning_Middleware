package rmi;

import java.rmi.Naming;
public class ClientRMI {
    public static void main(String[] args) {
        int listerPort=9912;//设置RMI监听器在9911端口,1099是默认端口
        String serverIP="localhost";//监听的IP10.12.8.157
        String serviceObjName="service";//要导出的服务对象名字
        try {
            //查找服务器上的服务对象
            Service stub = (Service)
                    Naming.lookup("rmi://"+serverIP+":"+listerPort+"/"+serviceObjName);
            String response = stub.searchByStart("北京首都机场");
            System.out.println("从北京机场出发的所有航班:" + response);

            response = stub.searchByTime();
            System.out.println("到达时间在10：00之前的所有航班:" + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}
