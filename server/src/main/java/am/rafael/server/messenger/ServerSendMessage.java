package am.rafael.server.messenger;

import am.rafael.my_proxy.message.ToClient;
import am.rafael.my_proxy.message.ToServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerSendMessage extends UnicastRemoteObject implements ToServer {
    List<ToClient> users = new ArrayList<>();
    public ServerSendMessage() throws RemoteException {
    }

    public void login(ToClient user) throws RemoteException {
        users.add(user);
        System.out.println(user.getName() + " - connected.");
        sendM(user.getName() + " - connected.");
    }

    public void sendM(String message) throws RemoteException {
        for(ToClient user : users){
            if(user!=null) {
                user.message(message);
            }
        }
    }
}
