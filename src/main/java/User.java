import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class User {

    private int id;
    private String email;
    private String userName;
    private String password;

    protected static User [] users;


    public User() {



    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    static User createUserObjectFromDatabase(User user,Scanner scannerIn)
    {
//         user =new User();
//         user.setUserName();


//            System.out.println("Podaj nazwe nowego uzytkownika");
//            user.setUserName( scannerIn.nextLine());
//            System.out.println("Podaj email nowego uzytkownika");
//            user.setEmail(scannerIn.nextLine());;
//            System.out.println("Podaj haslo nowego uzytkownika");
//            user.setPassword(scannerIn.nextLine());
//            System.out.println("Id nowego uzytkownika to:"+user.getId());



        return user;
    }
    static void exit()
    {
        System.exit(0);
    }
    static void showMenu()
    {
        System.out.println("Co chcesz zrobic?");
        System.out.println("1. Wyswietlic wszystkich uzytkownikow");
        System.out.println("2. Dodac uzytkownika");
        System.out.println("3. Usunac uzytkownika");
        System.out.println("4. Edytować uzytkownika");
        System.out.println("5. Wyswietlic dane uzykownika");
        System.out.println("6. Wyjscie z programu");
    }


    static void choise(Connection connection, Scanner scannerIn) throws SQLException {
        byte choise=scannerIn.nextByte();
        scannerIn.nextLine();
      while(true)
      {
        switch (choise) {
            case 1:
                UserDao.showAllUsers(connection);
                showMenu();
                choise(connection,scannerIn);
                break;
            case 2:
               UserDao.insertUserToDatabase(connection,scannerIn);
                showMenu();
                choise(connection,scannerIn);
                break;
            case 3:
                UserDao.deleteUserFromDatabase(connection,scannerIn);
                showMenu();
                choise(connection,scannerIn);
                break;
            case 4:
                UserDao.updateUserInDatabase(connection,scannerIn);
                showMenu();
                choise(connection,scannerIn);
                break;
            case 5:
                UserDao.showUserInfo(connection,scannerIn);
                showMenu();
                choise(connection,scannerIn);
                break;
            case 6:
                System.out.println("Dziekuje za skorzystanie z mojego programu.");
                exit();
                break;

        }
    }
}}
