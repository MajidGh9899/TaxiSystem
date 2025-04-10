package ir.maktab127.repository;


import ir.maktab127.entities.Passenger;

import java.util.Optional;

public interface PassengerRepository extends Repository {

    Optional<Passenger> getByUserName(String username);
    void save(Passenger passenger);



}
