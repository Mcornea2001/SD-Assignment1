package database;

public class User {
    private int id;
    private String userName;
    private String password;
    private int type; //1 - buyer, 2 - cashier, 3 - admin

    public User(int id, String userName, String password, int type) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", type=" + type + "]";
    }
}
