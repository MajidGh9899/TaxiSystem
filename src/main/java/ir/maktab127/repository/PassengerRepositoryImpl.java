package ir.maktab127.repository;



import ir.maktab127.entities.Passenger;

import java.sql.*;
import java.util.Optional;

public class PassengerRepositoryImpl extends RepositoryImpl implements PassengerRepository{

    public PassengerRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Passenger> getByUserName(String username) {

        try(PreparedStatement statement=connection.prepareStatement("select * from passengers where username=?")){
            statement.setString(1,username);

            ResultSet resultSet = statement.executeQuery();
            Passenger passenger=null;
            if(resultSet.next()){
                 passenger=new Passenger(resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("name"));
                 passenger.setId(resultSet.getLong("id"));

            }
            return Optional.ofNullable(passenger);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Passenger passenger) {

        try(PreparedStatement statement=connection.prepareStatement("insert into passengers values (?,?,?,?,?)")){
            statement.setLong(1,passenger.getId());
            statement.setString(2,passenger.getUserName());
            statement.setString(3,passenger.getPassword());
            statement.setString(4,passenger.getName());
            statement.setBoolean(5,passenger.isOnTrip());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
