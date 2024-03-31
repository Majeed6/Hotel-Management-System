package HotalManagementSystem;
import java.sql.*;

public class Connector {
    Connection c;
    Statement s;

    public Connector(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel2?useSSL=false", "root", "AymanBro123");
            s = c.createStatement();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }
}
