package ir.maktab127.entities;

public class Driver extends User{
    private  String carName;
    private String licencePlate;

    public Driver(String userName, String password,String name,String carName,String licencePlate) {
        super(userName, password,name);
        this.carName=carName;
        this.licencePlate=licencePlate;
    }
}
