package ir.maktab127.services;

public interface LoginService {
    boolean passengerLogin(String username,String Password);
    void passengerRegister(String username, String password,String name);
}
