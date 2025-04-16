package ir.maktab127;

import java.sql.SQLException;

import ir.maktab127.config.ApplicationContext;
import ir.maktab127.entities.Driver;
import ir.maktab127.entities.Location;
import ir.maktab127.entities.Passenger;
import ir.maktab127.services.LoginService;
import ir.maktab127.services.TripService;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        ApplicationContext applicationContext = ApplicationContext.getInstance();

        Thread thread = new Thread(() -> {
            try {
                
                LoginService loginService = applicationContext.getLoginService();
                Passenger passenger = loginService.passengerLogin("ali", "123");
                TripService tripService = applicationContext.getTripService();
                tripService.create(passenger, new Location(5, 4), new Location(10, 10));


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        });

        Thread thread1 = new Thread(
                () -> {
                    try {
                        LoginService loginService = applicationContext.getLoginService();
                        Passenger passenger = loginService.passengerLogin("sara", "145");
                        TripService tripService = applicationContext.getTripService();
                        tripService.create(passenger,new Location(1,1),new Location(8,13));


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });

        TripService tripService = applicationContext.getTripService();



        Thread driverThread = new Thread(
                ()->{
                    try {
                        LoginService loginService = applicationContext.getLoginService();
                        Driver driver = loginService.driverLogin("reza", "789");

                        tripService.acceptTrip(driver);
                    } catch (SQLException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );











        thread.start();
        thread1.start();
        driverThread.start();
        try {
            thread.join();
            thread1.join();
            driverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All threads have finished");





    }
}