package ir.maktab127.services;

import entities.Driver;
import entities.Location;
import entities.Passenger;
import entities.Trip;

public interface TripService {
    Trip Create(Passenger passenger, Location begin, Location destination);
    void setDriver(Trip trip,Driver driver);

}
