package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class Abstract<T> {
    private Connect conn;

    public Abstract(Connect conn) {
        this.conn = conn;
    }

    public abstract List<T> findAll() throws SQLException;

    public abstract boolean insert(T t) throws SQLException;

    public abstract boolean update(T t) throws SQLException;

    public abstract boolean delete(T t) throws SQLException;

    public Connection connect() {
        return conn.connect();
    }
}
