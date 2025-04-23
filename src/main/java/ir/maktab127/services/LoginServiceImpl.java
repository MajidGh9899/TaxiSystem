package ir.maktab127.services;

import ir.maktab127.entities.Driver;
import ir.maktab127.entities.Passenger;
import ir.maktab127.repository.DriverRepository;
import ir.maktab127.repository.DriverRepositoryImpl;
import ir.maktab127.repository.PassengerRepository;
import ir.maktab127.repository.PassengerRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class LoginServiceImpl implements LoginService{

    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;

    public LoginServiceImpl(Connection connection) throws SQLException {
        passengerRepository = new PassengerRepositoryImpl(connection);
        driverRepository = new DriverRepositoryImpl(connection);
    }

    @Override
    public Passenger passengerLogin(String username, String Password) {
        Optional<Passenger> byUserName = passengerRepository.getByUserName(username);
        if (byUserName.isPresent()) {
            if (byUserName.get().getPassword().equals(Password)) {
                return byUserName.get();
            }
            throw new RuntimeException("wrong password");
        }
        throw new RuntimeException("wrong username");
    }

    @Override
    public void passengerRegister(String username, String password, String name) {
        passengerRepository.save(new Passenger(username, password, name));
    }
    
    @Override
    public Driver driverLogin(String username, String password) throws SQLException {
        Optional<Driver> byUserName = driverRepository.getByUserName(username);
        if (byUserName.isPresent()) {
            if (byUserName.get().getPassword().equals(password)) {
                return byUserName.get();
            }
            throw new RuntimeException("wrong password");
        }
        throw new RuntimeException("wrong username");
    }
    
    @Override
    public Driver driverRegister(String username, String password, String name, String carName, String licensePlate) {
        return driverRepository.save(new Driver(username, password, name, carName, licensePlate));
    }
}
