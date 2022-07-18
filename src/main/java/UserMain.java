import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserMain {

    public static void main(String[] args) throws SQLException {


        //DbUtill.workshop2Connection();

//        User user1 = new User();
//        // user1.setId(0);
//        user1.setUserName("tomek1");
//        user1.setEmail("tkap@gmail.com11");
//        user1.setPassword("pass11");
//
//        User user2 = new User();
//
//        user2.setUserName("damian");
//        ;
//        user2.setEmail("damian@o2.pl");
//        user2.setPassword("pass2");

//        System.out.println(user1);
//
//        System.out.println(user2);


//


        // DZIALA :D

        try (Connection connection = DbUtill.getConnection();
             Scanner scannerIn = new Scanner(System.in)) {

     //       User.showMenu();
     //       User.choise(connection, scannerIn);
            //    User user5=UserDao.showUserInfo(connection,scannerIn);
            //  System.out.println(user5);
//            System.out.println(Arrays.toString(UserDao.showAllUsers(connection)));
        } catch (Exception ex) {
            ex.printStackTrace();

        }
//        } catch(SQLException ex)
//        {
//            ex.printStackTrace();
//        }
//
//    }
//}
    }}
