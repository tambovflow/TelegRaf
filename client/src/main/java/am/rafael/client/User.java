package am.rafael.client;

import am.rafael.my_proxy.message.ToClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class User extends UnicastRemoteObject implements ToClient {
    private String userName;
    protected User(String userName) throws RemoteException {
        super();
        this.userName = userName;
    }

    @Override
    public String getName() throws RemoteException {
        return this.userName;
    }

    @Override
    public void message(String message) throws RemoteException {
        System.out.println(message);
    }
}
