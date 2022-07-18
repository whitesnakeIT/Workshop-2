import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;
//import java.sql.*;

public class UserDao {



    private static final String SELECT_ALL = "SELECT * FROM users";

    private static final String INSERT_USER = "INSERT INTO users(email,username,password) VALUES (?,?,?)";

    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";

    private static final String UPDATE_USER = "UPDATE users SET email=?, username=?, password=?  WHERE id=? ";

    private static final String USER_INFO = "SELECT * FROM users WHERE id=?";

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private static User[] addToArray(User[] users, User user) {
        User[] temp = Arrays.copyOf(users, users.length + 1);
        temp[users.length] = user;
        return temp;
    }

    static User[] showAllUsers(Connection connection) throws SQLException {

        System.out.println("\nLista wszystkich użytkowników:\n");
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        User[] users = new User[0];


        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setEmail(resultSet.getString(2));
            user.setUserName(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));

            users = addToArray(users, user);
          int   id = resultSet.getInt(1);

           String email = resultSet.getString(2);
           String  userName = resultSet.getString(3);
           String  password = resultSet.getString(4);

            System.out.println("ID= " + id + " username= " + userName + " email= " + email + " password= " + password);


        }

        System.out.println();
        // System.out.println(User.addUser());

        return users;
    }

    static User insertUserToDatabase(Connection connection, Scanner scannerIn) throws SQLException {

        User user = new User();
        PreparedStatement preaparedStatement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);

        System.out.println("Podaj email nowego uzytkownika");
        String newEmail = scannerIn.nextLine();
        preaparedStatement.setString(1, newEmail);
        System.out.println("Podaj nazwe nowego uzytkownika");
        String newName = scannerIn.nextLine();
        preaparedStatement.setString(2, newName);

        System.out.println("Podaj haslo nowego uzytkownika");
        String newPassword = scannerIn.nextLine();

        newPassword = hashPassword(newPassword);
        preaparedStatement.setString(3, newPassword);
        //   System.out.println("Id nowego uzytkownika niezmienione to:"+user.getId());
        System.out.println("Uzytkownik zostal pomyslnie dodany.");
//
        preaparedStatement.executeUpdate();
        ResultSet resultSet = preaparedStatement.getGeneratedKeys();

        //User.users[0]=user;

        if (resultSet.next()) {
            int nextId = resultSet.getInt(1);
            System.out.println("INSERTED ID =" + nextId);
            user.setId(nextId);
        }
        user.setEmail(newEmail);
        user.setUserName(newName);
        user.setPassword(newPassword);

        System.out.println(user);

        return user;
    }

    static void deleteUserFromDatabase(Connection connection, Scanner scannerIn) throws SQLException {

        showAllUsers(connection);
        PreparedStatement preaparedStatement = connection.prepareStatement(DELETE_USER);
        System.out.println("Ktorego uzytkownika chcesz usunac ?");
        byte deleter = scannerIn.nextByte();

        preaparedStatement.setByte(1, deleter);
        preaparedStatement.executeUpdate();

        System.out.println("Uzytkownik o nr " + deleter + " zostal pomyslnie usuniety.");


    }

    static void updateUserInDatabase(Connection connection, Scanner scannerIn) throws SQLException {

        showAllUsers(connection);
        PreparedStatement preaparedStatement = connection.prepareStatement(UPDATE_USER);
        System.out.println("Ktorego uzytkownika chcesz edytowac ?");
        byte edit = scannerIn.nextByte();
        scannerIn.nextLine();
        String update;
        System.out.println("Podaj nowy email uzytkownika");
        String updatedEmail = scannerIn.nextLine();
        preaparedStatement.setString(1, updatedEmail);
        System.out.println("Podaj nowa nazwe uzytkownika");
        String updatedName = scannerIn.nextLine();
        preaparedStatement.setString(2, updatedName);
        System.out.println("Podaj nowe hasla uzytkownika");
        String updatedPassword = scannerIn.nextLine();
        updatedPassword = hashPassword(updatedPassword);
        preaparedStatement.setString(3, updatedPassword);
        preaparedStatement.setByte(4, edit);


        preaparedStatement.executeUpdate();

        System.out.println("Uzytkownik o nr " + edit + " zostal pomyslnie edytowany.");


    }

    public static User showUserInfo(Connection connection, Scanner scannerIn) throws SQLException {


        showAllUsers(connection);
        PreparedStatement preparedStatement = connection.prepareStatement(USER_INFO);

        System.out.println("O ktorym uzytkowniku chcesz wyswietlic dane?");
        byte info = scannerIn.nextByte();
        //  scannerIn.nextLine();

        preparedStatement.setByte(1, info);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            int id = resultSet.getInt(1);

            String email = resultSet.getString(2);
            String userName = resultSet.getString(3);
            String password = resultSet.getString(4);

            user.setId(resultSet.getInt(1));
            user.setEmail(resultSet.getString(2));
            user.setUserName(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));

            System.out.println("ID= " + id + " username= " + userName + " email= " + email + " password= " + password);

            return user;
        }


        System.out.println();

        return null;
    }

    public static void main(String[] args) {


    }


}

