package business;

import persistence.Connect;

import java.sql.SQLException;
import java.util.Scanner;
import persistence.UserDAO;
import database.User;
import java.sql.Timestamp;

public class Main {
    private static Connect conn;

    public static void main(String[] args) throws SQLException {
        conn = new Connect();
        UserDAO userDAO = new UserDAO(conn);
        //userDAO.insert(new User(3, "cashier1", "1234", 2));
        System.out.println(userDAO.findById(1).getPassword());

        Scanner scan= new Scanner(System.in);
        Cashier cashierBL = new Cashier(conn);
        System.out.println("Enter username: ");
        String text= scan.nextLine();

        while(userDAO.findUser(text) == -1){
            System.out.println("User not found");
            System.out.println("Enter username: ");
            text= scan.nextLine();
        }

        User user = userDAO.findById(userDAO.findUser(text));
        System.out.println("Enter password: ");
        String password = scan.nextLine();

        while (password.equals(user.getPassword()) == false)
        {
            System.out.println("Wrong password");
            System.out.println("Enter password: ");
            password = scan.nextLine();
        }
        System.out.println("Welcome " + user.getUserName());

        if(user.getType() == 2){
            Cashier cashier = new Cashier(conn);
            cashier.sellTicket(1,1,1,30, "Random user");
        }
        else if(user.getType() == 3){
            Admin admin = new Admin(conn);
            admin.addConcert(4, "Concert4", "Description4", new Timestamp(System.currentTimeMillis()), 100);
            System.out.println(admin.seeAllConcerts());
            admin.deleteConcert(4, "Concert4", "Description4", new Timestamp(System.currentTimeMillis()), 100);
        }
    }
}
