package persistence;
import database.Concert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ConcertDAO extends Abstract<Concert> {

    public static final String CREATE_SQL = "Insert into concerts(id, name, description, time, numberTickets) values(?, ?, ?, ?, ?)";
    public static final String READ_SQL = "Select * from concerts where id = ?";
    public static final String UPDATE_SQL = "Update concerts set name = ?, description = ?, time = ?, numberTickets = ? where id = ?";
    public static final String DELETE_SQL = "Delete from concerts where id = ?";
    public static final String NR_TICKETS = "Update concerts set numberTickets = concerts.numberTickets - 1 where id = ?";

    public ConcertDAO(Connect conn) {
        super(conn);
    }

    @Override
    public List<Concert> findAll() throws SQLException {
        List<Concert> concerts = new ArrayList<>();
        String sql = "SELECT * FROM concerts";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Concert concert = new Concert(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getTimestamp("time"), rs.getInt("numberTickets"));
                concerts.add(concert);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return concerts;
    }

    public Concert findById(int id) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(READ_SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Concert(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getTimestamp("time"), rs.getInt("numberTickets"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(Concert concert) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(CREATE_SQL)) {
            pstmt.setInt(1, concert.getId());
            pstmt.setString(2, concert.getName());
            pstmt.setString(3, concert.getDescription());
            pstmt.setTimestamp(4, concert.getTime());
            pstmt.setInt(5, concert.getNumberOfTickets());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Concert concert) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            pstmt.setInt(1, concert.getId());
            pstmt.setString(2, concert.getName());
            pstmt.setString(3, concert.getDescription());
            pstmt.setTimestamp(4, concert.getTime());
            pstmt.setInt(5, concert.getNumberOfTickets());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Concert concert) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            pstmt.setInt(1, concert.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean nrTickets(int id) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(NR_TICKETS)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
