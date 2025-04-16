package ir.maktab127.repository;

import ir.maktab127.entities.Location;
import ir.maktab127.entities.Passenger;
import ir.maktab127.entities.Trip;
import ir.maktab127.entities.TripStatus;
import ir.maktab127.entities.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripRepositoryImpl extends RepositoryImpl implements TripRepository {


    public TripRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Trip trip) throws SQLException {

        try(PreparedStatement statement=connection.prepareStatement("insert into trip (passenger_id, price, begin, destination,status) values (?,?,?,?,?)")){

            statement.setInt(1,trip.getPassenger().getId());


            statement.setDouble(2,trip.getPrice());
            try(PreparedStatement prep=connection.prepareStatement("insert into location (x, y) values (?,?) ", Statement.RETURN_GENERATED_KEYS )){
                prep.setInt(1,trip.getBegin().getX());
                prep.setInt(2,trip.getBegin().getY());
                prep.executeUpdate();
                ResultSet generatedKeys = prep.getGeneratedKeys();
                if(generatedKeys.next()) {
                    statement.setInt(3, generatedKeys.getInt(1));
                }
                    prep.setInt(1, trip.getDestination().getX());
                    prep.setInt(2, trip.getDestination().getY());
                    prep.executeUpdate();
                generatedKeys = prep.getGeneratedKeys();
                if(generatedKeys.next()) {

                statement.setInt(4,generatedKeys.getInt(1));
                }

            }

            statement.setString(5,trip.getTripStatus().name());
            statement.executeUpdate();




        }

    }

    @Override
    public void update(Trip trip) throws SQLException {

        try(PreparedStatement statement=connection.prepareStatement("update trip set driver_id=?,status=? where id=?")){
            statement.setInt(1,trip.getDriver().getId());
            statement.setString(2,trip.getTripStatus().name());
            statement.setInt(3,trip.getId());
            statement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<tripRecord> getAllWaitingTrips() {
        List<tripRecord> trips=new ArrayList<>();
        try(Statement statement=  connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select t.id,p.name,l.x,l.y,l2.x,l2.y,t.price from trip t  join passengers p on t.passenger_id=p.id join location l on t.begin=l.id and t.destination=l.id join location l2 on t.destination=l2.id\n" +
                    "    where t.status='WAITING'\n" +
                    "    and p.isTrip=false\n" +
                    "    and t.driver_id is null");




            while(resultSet.next()){

                tripRecord  tripRecord=new tripRecord(resultSet.getInt("t.id"),
                        resultSet.getString("p.name"),
                        new Location(resultSet.getInt("l.x"),resultSet.getInt("l.y")),
                        new Location(resultSet.getInt("l2.x"),resultSet.getInt("l2.y")),
                        resultSet.getDouble("t.price"));

                trips.add(tripRecord);



            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trips;
    }

    @Override
    public Trip getById(int id) throws SQLException {
        Trip trip=new Trip(null,null,null);

        try(PreparedStatement statement=connection.prepareStatement("select * from trip t join public.passengers p on p.id = t.passenger_id join drivers d on t.driver_id = d.id join location l on l.id = t.begin join location l2 on l2.id = t.destination  where id=?")){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                trip.setId(resultSet.getInt("id"));
                trip.setPrice(resultSet.getDouble("price"));
                trip.setTripStatus(TripStatus.valueOf(resultSet.getString("status")));
                trip.setBegin(new Location(resultSet.getInt("l.x"),resultSet.getInt("l.y")));
                trip.setDestination(new Location(resultSet.getInt("l2.x"),resultSet.getInt("l2.y")));
                trip.setPassenger(new Passenger(resultSet.getString("p.name"),resultSet.getString("p.password"),resultSet.getString("p.name")));
                trip.setDriver(new Driver(resultSet.getString("d.name"),resultSet.getString("d.password"),resultSet.getString("d.name"),resultSet.getString("d.carName"),resultSet.getString("d.licencePlate")));



            }

        }
        return trip;
    }


}
