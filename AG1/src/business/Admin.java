package business;

import database.User;
import persistence.Connect;
import database.Concert;

import persistence.ConcertDAO;
import persistence.UserDAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Admin {

    private Connect conn;

    public Admin(Connect conn) {
        this.conn = conn;
    }

    public List<Concert> seeAllConcerts() throws SQLException {
        ConcertDAO concertDAO = new ConcertDAO(conn);
        return concertDAO.findAll();
    }
    public Concert seeConcert(int id) throws SQLException {
        ConcertDAO concertDAO = new ConcertDAO(conn);
        return concertDAO.findById(id);
    }
    public void addConcert(int id, String name, String description, Timestamp time, int numberTickets) throws SQLException {
        Concert concert = new Concert(id, name, description,  time, numberTickets);
        ConcertDAO concertDAO = new ConcertDAO(conn);
        concertDAO.insert(concert);
    }
    public void updateConcert(int id, String name, String description, Timestamp time, int numberTickets) throws SQLException {
        Concert concert = new Concert(id, name, description,  time, numberTickets);
        ConcertDAO concertDAO = new ConcertDAO(conn);
        concertDAO.update(concert);
    }
    public void deleteConcert(int id, String name, String description, Timestamp time, int numberTickets) throws SQLException {
        Concert concert = new Concert(id, name, description,  time, numberTickets);
        ConcertDAO concertDAO = new ConcertDAO(conn);
        concertDAO.delete(concert);
    }
    public List<User> seeAllUsers() throws SQLException {
        UserDAO userDAO = new UserDAO(conn);
        return userDAO.findAll();
    }
    public User seeUser(int id) throws SQLException {
        UserDAO userDAO = new UserDAO(conn);
        return userDAO.findById(id);
    }
    public void createUser (int id, String username, String password, int type) throws SQLException {
        User user = new User(id, username, password, type);
        UserDAO userDAO = new UserDAO(conn);
        userDAO.insert(user);
    }

    public void updateUser (int id, String username, String password, int type) throws SQLException {
        User user = new User(id, username, password, type);
        UserDAO userDAO = new UserDAO(conn);
        userDAO.update(user);
    }
    public void deleteUser (int id, String username, String password, int type) throws SQLException {
        User user = new User(id, username, password, type);
        UserDAO userDAO = new UserDAO(conn);
        userDAO.delete(user);
    }
}
