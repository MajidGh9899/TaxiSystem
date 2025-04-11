package ir.maktab127.services;

import ir.maktab127.entities.Passenger;

public interface LoginService {
    Passenger passengerLogin(String username, String Password);
    void passengerRegister(String username, String password,String name);
}
