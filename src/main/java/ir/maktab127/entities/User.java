package ir.maktab127.entities;

public class User {

    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public User(String userName,String password,String name ){

        this.name=name;
        this.userName=userName;
        this.password=password;
        this.onTrip=false;




    }

}
