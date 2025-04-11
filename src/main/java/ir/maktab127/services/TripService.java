package ir.maktab127.services;


import ir.maktab127.entities.Driver;
import ir.maktab127.entities.Location;
import ir.maktab127.entities.Passenger;
import ir.maktab127.entities.Trip;

import java.sql.SQLException;

public interface TripService {
    Trip create(Passenger passenger, Location begin, Location destination);
    void setDriver(Trip trip, Driver driver);
    void getAllWaitingTrips() throws InterruptedException;
    void acceptTrip(Driver driver) throws InterruptedException, SQLException;

}
