package ir.maktab127.repository;

import ir.maktab127.entities.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TripRepositoryImpl extends RepositoryImpl implements TripRepository {

    public TripRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Trip trip) throws SQLException {
        try(PreparedStatement statement=connection.prepareStatement("insert into trip values (?,?,?,?,?,?,?,?,?)")){
            statement.setInt(1,trip.getId());
            statement.setInt(2,trip.getPassenger().getId());

            statement.setInt(3,trip.getDriver().getId());
            statement.setInt(4,trip.getBegin().getX());
            statement.setInt(5,trip.getBegin().getY());
            statement.setInt(6,trip.getDestination().getX());
            statement.setInt(7,trip.getDestination().getY());
            statement.setDouble(8,trip.getPrice());
            statement.setString(9,trip.getTripStatus().name());



        }

    }

    @Override
    public Trip update(Trip trip) {
        return null;
    }
}
