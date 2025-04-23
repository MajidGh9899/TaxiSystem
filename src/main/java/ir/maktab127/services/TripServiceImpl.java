package ir.maktab127.services;


import ir.maktab127.entities.*;
import ir.maktab127.repository.TripRepository;
import ir.maktab127.repository.TripRepository.*;
import ir.maktab127.repository.TripRepositoryImpl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    List<Trip> trips;


    public TripServiceImpl(Connection connection) {

        tripRepository=new TripRepositoryImpl(connection);
        trips=new ArrayList<>();

    }


    @Override
    public Trip create(Passenger passenger, Location begin, Location destination) {
        Trip trip = new Trip(passenger, begin, destination);
        try {

            trip=tripRepository.save(trip);
            synchronized (this) {
                if(trips.size()==1){
                    wait();
                }
                trips.add(  trip);
                System.out.println("passenger "+passenger.getName()+" created trip "+trip.getId()+" from "+begin.getX()+" "+begin.getY()+" to "+destination.getX()+" "+destination.getY()+" with price "+trip.getPrice());
                notifyAll();
            }
            return trip;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setDriver( Trip trip,  Driver driver) {
        trip.setDriver(driver);
        trip.setTripStatus(TripStatus.ONTRIP);
        try {
            tripRepository.update(trip);

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


//    public void getAllWaitingTrips() throws InterruptedException {
//
//        trips=tripRepository.getAllWaitingTrips();
//        while(true){
//            synchronized (trips){
//            if(!trips.isEmpty()){
//                wait();
//
//            }
//            trips=tripRepository.getAllWaitingTrips();
//            notifyAll();
//
//
//
//        }
//        }
//
//    }

    @Override
    public void acceptTrip( Driver driver) throws InterruptedException, SQLException {
        while(true){
        synchronized (this) {
            if (trips.isEmpty()) {

                wait();
            }
            Trip trip = trips.removeFirst();


            trip.setDriver(driver);
            trip.setTripStatus(TripStatus.ONTRIP);
            tripRepository.update(trip);
            driver.setOnTrip(true);
            System.out.println("driver " + driver.getName() + " accepted trip " + trip.getId() + " from " + trip.getPassenger().getName() + " to " + " with price " + trip.getPrice() + "");
            notifyAll();
            trip.tripTime();
            trip.setTripStatus(TripStatus.FINISHED);
            tripRepository.update(trip);
            driver.setOnTrip(false);
            System.out.println("driver " + driver.getName() + " finished trip " + trip.getId() + " from " + trip.getPassenger().getName() + " to " + " with price " + trip.getPrice() + "");

        }
        }


    }
}
