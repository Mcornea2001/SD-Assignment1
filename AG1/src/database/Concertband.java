package database;

public class Concertband {
    private int id;
    public int concertId;
    public int bandId;

    public Concertband(int id, int concertId, int bandId) {
        this.id = id;
        this.concertId = concertId;
        this.bandId = bandId;
    }

    public int getId() {
        return id;
    }

    public int getConcertId() {
        return concertId;
    }

    public int getBandId() {
        return bandId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConcertId(int concertId) {
        this.concertId = concertId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String toString() {
        return id + " " + concertId + " " + bandId;
    }
}
