package am.rafael.server;

import am.rafael.server.database.ChatDatabase;
import am.rafael.server.messenger.ServerSendMessage;
import am.rafael.server.users.UserRegistratorImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ChatDatabase.mysqlLogin();
        System.out.println("Server has started");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("login", new UserRegistratorImpl());
        registry.bind("chat", new ServerSendMessage());
    }
}
