package ir.maktab127.repository;

import ir.maktab127.entities.Driver;
import ir.maktab127.entities.Trip;

import java.sql.SQLException;

public interface TripRepository extends Repository {
     void save(Trip trip) throws SQLException;
     Trip update(Trip trip);




}
