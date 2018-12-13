package am.rafael.my_proxy.user;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface UserRegistrator extends Remote {
    int regUser(String login, String pass) throws RemoteException;
}
