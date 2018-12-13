package am.rafael.server.database;

import java.sql.*;
import java.util.Scanner;

public class ChatDatabase {
    private static String u,p;
    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection;

    public static void mysqlLogin(){
        do {
            System.out.println("please enter correct mysql user and pass");
            System.out.print("mysql user: ");
            u = scanner.nextLine();
            System.out.print("password: ");
            p = scanner.nextLine();
        }while (!DDL.getCon(u,p));

        connection = getConnection();
    }
    private static Connection getConnection() {
        try {

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/raf_chat_clients_2018?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    u,
                    p
            );
            System.out.println("Connection successful");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int registredUser(String login, String pass) {
        String check = "SELECT pass from users where login=?";
        String insert = "INSERT INTO users (login, pass) values (?,?)";

        try {
            PreparedStatement checkStatment = connection.prepareStatement(check);
            checkStatment.setString(1, login);
            ResultSet resultSet = checkStatment.executeQuery();
            boolean bool = resultSet.next();
            if (bool && resultSet.getString(1).equals(pass)) {
                System.out.println(login + " joined!");
                return 2;
            } else if (!bool) {
                PreparedStatement insertStatment = connection.prepareStatement(insert);
                insertStatment.setString(1, login);
                insertStatment.setString(2, pass);
                System.out.println(login + " user created!");
                return insertStatment.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
