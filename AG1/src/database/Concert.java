package database;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Concert {
    private int id;
    private String name;
    private String description;
    private Timestamp time;
    private int numberOfTickets;

    public Concert(int id, String name, String description, Timestamp time, int numberOfTickets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.numberOfTickets = numberOfTickets;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
