package ir.maktab127.entities;

public class User {
    private   static int idx=0;
    private int id;
    private String name;
    private String userName;
    private String password;
    public String getName() {
        return name;
    }
    public boolean isOnTrip() {
        return onTrip;
    }

    public void setOnTrip(boolean onTrip) {
        this.onTrip = onTrip;
    }

    private boolean onTrip;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public User(String userName,String password,String name ){
        id=id++;
        this.name=name;
        this.userName=userName;
        this.password=password;
        this.onTrip=false;




    }

}
