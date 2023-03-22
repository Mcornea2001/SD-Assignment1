package persistence;
import database.Concertband;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConcertBandDAO extends Abstract<Concertband>{

    public static final String CREATE_SQL = "Insert into concertbands(id, concertId, bandID) values(?, ?, ?)";
    public static final String READ_SQL = "Select * from concertbands where id = ?";
    public static final String UPDATE_SQL = "Update concertbands set concertId = ?, bandId = ? where id = ?";
    public static final String DELETE_SQL = "Delete from concertbands where id = ?";

    public ConcertBandDAO(Connect conn) {
        super(conn);
    }

    @Override
    public List<Concertband> findAll() throws SQLException {
        List<Concertband> concertbands = new ArrayList<>();
        String sql = "SELECT * FROM concertbands";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Concertband concertband = new Concertband(rs.getInt("id"), rs.getInt("concertId"), rs.getInt("bandId"));
                concertbands.add(concertband);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return concertbands;
    }

    public Concertband findById(int id) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(READ_SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Concertband(rs.getInt("id"), rs.getInt("concertId"), rs.getInt("bandId"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(Concertband concertband) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(CREATE_SQL)) {
            pstmt.setInt(1, concertband.getId());
            pstmt.setInt(2, concertband.getConcertId());
            pstmt.setInt(3, concertband.getBandId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Concertband concertband) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            pstmt.setInt(1, concertband.getId());
            pstmt.setInt(2, concertband.getConcertId());
            pstmt.setInt(3, concertband.getBandId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Concertband concertband) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            pstmt.setInt(1, concertband.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
