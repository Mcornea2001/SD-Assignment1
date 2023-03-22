package persistence;
import database.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TicketsDAO extends Abstract<Ticket>{

    public static final String CREATE_SQL = "Insert into tickets(id, concertId, userId, price, name) values(?, ?, ?, ?, ?)";
    public static final String READ_SQL = "Select * from tickets where id = ?";
    public static final String UPDATE_SQL = "Update tickets set concertId = ?, userId = ?, price = ?, name = ? where id = ?";
    public static final String DELETE_SQL = "Delete from tickets where id = ?";

    public TicketsDAO(Connect conn) {
        super(conn);
    }

    @Override
    public List<Ticket> findAll() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("id"), rs.getInt("concertId"), rs.getInt("userId"), rs.getDouble("price"), rs.getString("name"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tickets;
    }

    public Ticket findById(int id) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(READ_SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Ticket(rs.getInt("id"), rs.getInt("concertId"), rs.getInt("userId"), rs.getDouble("price"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public boolean insert(Ticket ticket) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(CREATE_SQL)) {
            pstmt.setInt(1, ticket.getId());
            pstmt.setInt(2, ticket.getConcertId());
            pstmt.setInt(3, ticket.getUserId());
            pstmt.setDouble(4, ticket.getPrice());
            pstmt.setString(5, ticket.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Ticket ticket) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            pstmt.setInt(1, ticket.getConcertId());
            pstmt.setInt(2, ticket.getUserId());
            pstmt.setDouble(3, ticket.getPrice());
            pstmt.setString(4, ticket.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Ticket ticket) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            pstmt.setInt(1, ticket.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
