package ir.maktab127.entities;

public class Driver extends User{
    private String carName;
    private String licensePlate;

    public Driver(String userName, String password, String name, String carName, String licensePlate) {
        super(userName, password, name);
        this.carName = carName;
        this.licensePlate = licensePlate;
    }
    
    public String getCarName() {
        return carName;
    }
    
    public void setCarName(String carName) {
        this.carName = carName;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
