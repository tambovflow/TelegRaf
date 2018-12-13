package am.rafael.server.users;

import am.rafael.my_proxy.user.UserRegistrator;
import am.rafael.server.database.ChatDatabase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserRegistratorImpl extends UnicastRemoteObject implements UserRegistrator {


    public UserRegistratorImpl() throws RemoteException {
    }

    public int regUser(String login, String pass) {
        login = login.toLowerCase().trim().replaceAll(" ","_");
        pass = pass.replaceAll(" ", "");
        return ChatDatabase.registredUser(login,pass);
    }
}
