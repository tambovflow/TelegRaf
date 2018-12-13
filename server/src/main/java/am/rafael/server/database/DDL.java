package am.rafael.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DDL {

    public static boolean getCon(String u, String p){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", u, p)){
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS raf_chat_clients_2018");
            statement.execute("USE raf_chat_clients_2018");
            statement.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "    id int not null AUTO_INCREMENT UNIQUE," +
                    "    login varchar(25) not null UNIQUE PRIMARY KEY ," +
                    "    pass varchar(25) not null," +
                    "    reg_data timestamp not null default now()" +
                    ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
