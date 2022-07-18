import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtill

{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    static void workshop2Connection()
    {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8&serverTimezone=UTC","root","coderslab"))
        {
            if(connection.isClosed())
            {
                System.out.println("Brak polaczenia z baza");
            }
            else
            {
                System.out.println("Polaczono z baza danych");
            }

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }



   // public static final String USERS_CONNECTION();

    public static void main(String[] args) {

    }
}
