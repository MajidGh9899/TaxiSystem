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
    List<tripRecord> trips;


    public TripServiceImpl(Connection connection) {

        tripRepository=new TripRepositoryImpl(connection);
        trips=new ArrayList<>();

    }


    @Override
    public Trip create(Passenger passenger, Location begin, Location destination) {
        Trip trip = new Trip(passenger, begin, destination);
        try {

            tripRepository.save(trip);
            synchronized (trips) {
                if(trips.size()==1){
                    wait();
                }
                trips.add(  new tripRecord(trip.getId(),passenger.getName(),begin,destination,trip.getPrice()));
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
        synchronized (trips) {
            if (trips.isEmpty()) {
                wait();
            }
            tripRecord trip = trips.removeFirst();
            Trip resultTrip = tripRepository.getById(trip.id());
            resultTrip.setDriver(driver);
            resultTrip.setTripStatus(TripStatus.ONTRIP);
            tripRepository.update(resultTrip);
            driver.setOnTrip(true);
            System.out.println("driver "+driver.getName()+" accepted trip "+trip.id()+" from "+trip.passengerName()+" to "+" with price "+trip.price()+"");
            notifyAll();

        }


    }
}
