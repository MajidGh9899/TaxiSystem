package ir.maktab127;

import ir.maktab127.config.ApplicationContext;
import ir.maktab127.entities.Location;
import ir.maktab127.entities.Passenger;
import ir.maktab127.services.LoginService;
import ir.maktab127.services.TripService;

import java.sql.SQLException;

public class Main {
    public static <Drivers> void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContext.getInstance();

        Thread
                thread = new Thread(() -> {
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

        Thread driverThread = new Thread(
                ()->{


                }
        );



    }
}