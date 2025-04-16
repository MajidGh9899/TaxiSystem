package ir.maktab127.services;

import ir.maktab127.entities.Driver;
import ir.maktab127.entities.Passenger;

import java.sql.SQLException;

public interface LoginService {
    Passenger passengerLogin(String username, String Password);
    void passengerRegister(String username, String password, String name);
    
   
    Driver driverLogin(String username, String password) throws SQLException;
    void driverRegister(String username, String password, String name, String carName, String licensePlate);
}
