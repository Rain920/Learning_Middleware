package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    public String searchByStart(String start) throws RemoteException, Exception;
    public String searchByTime() throws RemoteException, Exception;
}
