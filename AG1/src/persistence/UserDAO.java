package persistence;
import database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserDAO extends Abstract<User> {

    Base64.Encoder encoder = Base64.getEncoder();
    Base64.Decoder decoder = Base64.getDecoder();
    public static final String CREATE_SQL = "Insert into user(id, username, password, type) values(?, ?, ?, ?)";
    public static final String READ_SQL = "Select * from user where id = ?";
    public static final String READ_USER_SQL = "Select * from user where username = ?";
    public static final String UPDATE_SQL = "Update user set username = ?, password = ?, type = ? where id = ?";
    public static final String DELETE_SQL = "Delete from user where id = ?";

    public UserDAO(Connect conn) {
        super(conn);
    }
    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("userName"), new String(decoder.decode(rs.getString("password"))), rs.getInt("type"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User findById(int id) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(READ_SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("userName"), new String(decoder.decode(rs.getString("password"))), rs.getInt("type"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int findUser(String userName) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(READ_USER_SQL)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    @Override
    public boolean insert(User user) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(CREATE_SQL)) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, encoder.encodeToString(user.getPassword().getBytes()));
            pstmt.setInt(4, user.getType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(User user) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, encoder.encodeToString(user.getPassword().getBytes()));
            pstmt.setInt(4, user.getType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
