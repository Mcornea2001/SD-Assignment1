package business;

import database.Ticket;
import persistence.ConcertDAO;
import persistence.Connect;
import persistence.TicketsDAO;

import java.sql.SQLException;
import java.util.List;

public class Cashier {
    private Connect conn;

    public Cashier(Connect conn) {
        this.conn = conn;
    }
    public void sellTicket(int id, int concertId, int userId, double price, String name) throws SQLException {
        Ticket ticket = new Ticket(id, concertId, userId, price, name);
        TicketsDAO ticketsDAO = new TicketsDAO(conn);
        ticketsDAO.insert(ticket);
        ConcertDAO concertDAO = new ConcertDAO(conn);
        concertDAO.nrTickets(concertId);
    }

    public List<Ticket> seeTickets() throws SQLException {
        TicketsDAO ticketsDAO = new TicketsDAO(conn);
        return ticketsDAO.findAll();
    }
}
