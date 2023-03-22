package database;

public class Ticket {
    private int id;
    private double price;
    public int concertId;
    public int userId;
    private String name;

    public Ticket(int id, int concertId, int userId, double price, String name) {
        this.id = id;
        this.price = price;
        this.concertId = concertId;
        this.userId = userId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getConcertId() {
        return concertId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString () {
        return id + " " + concertId + " " + userId + " " + price + " " + name;
    }
}
