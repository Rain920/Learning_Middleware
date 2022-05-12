package rmi;

import utils.FlightSearch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class ServiceImpl extends UnicastRemoteObject implements Service{
    protected ServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String searchByStart(String start) throws Exception {
        return FlightSearch.searchByLeave(start);
    }

    @Override
    public String searchByTime() throws Exception {
        return FlightSearch.searchByTime();
    }
}
