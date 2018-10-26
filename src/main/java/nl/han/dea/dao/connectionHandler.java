package nl.han.dea.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectionHandler {


    static Properties properties;
    static Connection conn;
    private static connectionHandler connectionHandler = new connectionHandler();

    public static void main(String[] args) {
        connectionHandler.connectToDB();
    }

    public void connectToDB() {
        try {
            properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));

            conn = DriverManager.getConnection(properties.getProperty("connectionString"), properties.getProperty("user"), properties.getProperty("pass"));
            System.out.println("connectie werkt");
        } catch (SQLException e) {
            System.out.println("Error connecting to a database: " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

