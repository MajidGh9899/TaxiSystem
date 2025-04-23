package ir.maktab127.repository;

import ir.maktab127.entities.Driver;

import java.sql.*;
import java.util.Optional;

public class DriverRepositoryImpl extends RepositoryImpl implements DriverRepository{

    public DriverRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Driver> getByUserName(String username) throws SQLException {
        try(PreparedStatement statement=connection.prepareStatement("select * from drivers where username=?")){

            statement.setString(1,username);

           ResultSet resultSet = statement.executeQuery();
            Driver driver=null;
            if(resultSet.next()){
                driver=new Driver(resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("name"),resultSet.getString("car_name"),resultSet.getString("licence_plate"));
                driver.setId(resultSet.getLong("id"));

            }
            return Optional.ofNullable(driver);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public Driver save(Driver driver) {
        try(PreparedStatement statement=connection.prepareStatement("insert into drivers (username,password,name,car_name,licence_plate) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS )){

            statement.setString(1,driver.getUserName());
            statement.setString(2,driver.getPassword());
            statement.setString(3,driver.getName());
            statement.setString(4,driver.getCarName());
            statement.setString(5,driver.getLicensePlate());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                driver.setId(generatedKeys.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return driver;
    }
}
