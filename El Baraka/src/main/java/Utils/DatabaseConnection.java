package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url="jdbc:mysql://localhost:3306/albaraka";
    private static final String user="root";
    private static final String password="";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            return null;
        }
    }
}
