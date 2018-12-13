package am.rafael.client;

import am.rafael.my_proxy.message.ToServer;
import am.rafael.my_proxy.user.UserRegistrator;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    private static UserRegistrator uReg;
    private static ToServer server;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        start();
        System.out.println("Welcome to my simple chat!\nCreated by Rafael(2018)");
        loginUser();
    }

    public static void start() throws RemoteException, NotBoundException, MalformedURLException {
        uReg = (UserRegistrator) Naming.lookup("login");
        server = (ToServer) Naming.lookup("chat");
    }

    public static void loginUser() throws RemoteException {

        while (true){
            System.out.println("(S) -> Sign in");
            System.out.println("(A) -> Anonymous");
            String command = scanner.nextLine();

            switch (command.toUpperCase()){
                case "S": regUser();
                break;
                case "A": anonym();
                break;
            }
        }
    }

    public static void regUser() throws RemoteException {
        String[] logPass = userInput();
        int i = uReg.regUser(logPass[0], logPass[1]);
        if(i==1){
            System.out.println("New user " + logPass[0] + " is created");
        } else if(i==2){
            System.out.println("Welcome " + logPass[0]);
        }
        else if(i==0){
            System.out.println(logPass[0] + " username is already exists.\nEnter correct password");
        } else{
            System.out.println("Oops...");
        }

        if(i>0){
            chat(new User(logPass[0]));
        }
    }

    public static void anonym() throws RemoteException {
        chat(new User("Anonymous"));
    }

    public static void chat(User user) {

        try {
            String name = user.getName();
            server.login(user);
            while (true){
                server.sendM(name + ": " + scanner.nextLine().trim());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    
    public static String[] userInput(){
        String[] logPass = new String[2];
        System.out.print("Your login: ");
        logPass[0] = scanner.nextLine();
        System.out.print("Password: ");
        logPass[1] = scanner.nextLine();
        return logPass;
    }

}
