package ir.maktab127.repository;

import ir.maktab127.entities.Driver;
import ir.maktab127.entities.Location;
import ir.maktab127.entities.Trip;

import java.sql.SQLException;
import java.util.List;

public interface TripRepository extends Repository {
//      record tripRecord(Long id , String passengerName , Location begin, Location destination, double price){}

     Trip save(Trip trip) throws SQLException;
     void update(Trip trip) throws SQLException;
//     List<tripRecord> getAllWaitingTrips();
     Trip getById(Long id) throws SQLException;





}
