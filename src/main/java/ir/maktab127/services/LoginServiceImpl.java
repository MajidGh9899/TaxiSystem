package ir.maktab127.services;

import ir.maktab127.entities.Passenger;
import ir.maktab127.repository.PassengerRepository;
import ir.maktab127.repository.PassengerRepositoryImpl;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class LoginServiceImpl implements LoginService{

    PassengerRepository  passengerRepository=new PassengerRepositoryImpl(
            DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/taxi",
                    "postgres",
                    "123"
            )
    );

    public LoginServiceImpl() throws SQLException {

    }

    @Override
    public boolean passengerLogin(String username, String Password) {

        return passengerRepository.getByUserName(username).map(value -> value.getPassword().equals(Password)).orElse(false);
    }

    @Override
    public void passengerRegister(String username, String password,String name) {

         passengerRepository.save( new Passenger(username,password,name));
    }
}
