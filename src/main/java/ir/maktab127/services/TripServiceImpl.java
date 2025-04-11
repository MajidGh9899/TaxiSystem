package ir.maktab127.services;


import ir.maktab127.entities.*;
import ir.maktab127.repository.TripRepository;
import ir.maktab127.repository.TripRepository.*;
import ir.maktab127.repository.TripRepositoryImpl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    List<tripRecord> trips;

    public TripServiceImpl(Connection connection) {

        tripRepository=new TripRepositoryImpl(connection);
    }


    @Override
    public  Trip create( Passenger passenger,  Location begin,  Location destination) {
        return null;
    }

    @Override
    public void setDriver( Trip trip,  Driver driver) {

    }

    @Override
    public void getAllWaitingTrips() throws InterruptedException {

        trips=tripRepository.getAllWaitingTrips();
        while(true){
            synchronized (trips){
            if(!trips.isEmpty()){
                wait();

            }
            trips=tripRepository.getAllWaitingTrips();
            notifyAll();



        }
        }

    }

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
            notifyAll();
        }


    }
}
