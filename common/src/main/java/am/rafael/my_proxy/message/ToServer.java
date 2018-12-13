package am.rafael.my_proxy.message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ToServer extends Remote {
    void login(ToClient user) throws RemoteException;
    void sendM(String message) throws RemoteException;
}
