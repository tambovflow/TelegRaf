package am.rafael.my_proxy.message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ToClient extends Remote {
    String getName()throws RemoteException;
    void message(String message) throws RemoteException;
}
