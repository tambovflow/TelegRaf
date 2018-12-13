package am.rafael.server.messenger;

import am.rafael.my_proxy.message.ToClient;
import am.rafael.my_proxy.message.ToServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerSendMessage extends UnicastRemoteObject implements ToServer {
    Map<String, ToClient> users = new HashMap<String, ToClient>();
    public ServerSendMessage() throws RemoteException {
    }

    public void login(ToClient user) throws RemoteException {
        users.put(user.getName(), user);
        System.out.println(user.getName() + " - connected.");
        sendM(user.getName() + " - connected.");
    }

    public void sendM(String message) throws RemoteException {
        for(Map.Entry<String, ToClient> user : users.entrySet()){
            if(user.getValue()!=null) {
                user.getValue().message(message);
            }
        }
    }
}
