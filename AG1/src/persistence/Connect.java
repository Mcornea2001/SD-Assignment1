package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection connect(){
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:C:/Users/Mihai/DataGripProjects/untold/identifier.sqlite";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
