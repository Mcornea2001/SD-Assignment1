package persistence;
import database.Band;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandDAO extends Abstract<Band> {

    public static final String CREATE_SQL = "Insert into bands(id, name, genre) values(?, ?, ?)";
    public static final String RETRIEVE_SQL = "Select * from bands where id = ?";
    public static final String UPDATE_SQL = "Update bands set name = ?, genre = ? where id = ?";
    public static final String DELETE_SQL = "Delete from bands where id = ?";

    public BandDAO(Connect conn) {
        super(conn);
    }

    @Override
    public List<Band> findAll() throws SQLException {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Band band = new Band(rs.getInt("id"), rs.getString("name"), rs.getString("genre"));
                bands.add(band);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bands;
    }


    public Band findById(int id) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(RETRIEVE_SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Band(rs.getInt("id"), rs.getString("name"), rs.getString("genre"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(Band band) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(CREATE_SQL)) {
            pstmt.setInt(1, band.getId());
            pstmt.setString(2, band.getName());
            pstmt.setString(3, band.getGenre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Band band) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            pstmt.setString(1, band.getName());
            pstmt.setString(2, band.getGenre());
            pstmt.setInt(3, band.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Band band) throws SQLException {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            pstmt.setInt(1, band.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
