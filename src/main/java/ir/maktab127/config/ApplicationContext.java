package ir.maktab127.config;

import ir.maktab127.services.LoginService;
import ir.maktab127.services.LoginServiceImpl;
import ir.maktab127.services.TripService;
import ir.maktab127.services.TripServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ApplicationContext {
    private static ApplicationContext instance;

    private ApplicationContext() {

    }
    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();

        }
        return instance;
    }

    private Connection connection;

    public  Connection getConnection() throws SQLException {
        if(connection==null){
            connection= DriverManager.getConnection(ApplicationProperties.JDBC_URL, ApplicationProperties.JDBC_USER, ApplicationProperties.JDBC_PASSWORD);

        }
        return connection;

    }
    private TripService tripService;
    public TripService getTripService() throws SQLException {
        if(tripService==null){
            tripService=new TripServiceImpl(getConnection());
        }
        return tripService;
    }

    private LoginService loginService;
    public LoginService getLoginService() throws SQLException {
        if(loginService==null){
            loginService=new LoginServiceImpl(getConnection());
        }
        return loginService;
    }



    }




