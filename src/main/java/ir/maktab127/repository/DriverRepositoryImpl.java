package ir.maktab127.repository;

import ir.maktab127.entities.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            }
            return Optional.ofNullable(driver);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public void save(Driver driver) {
        try(PreparedStatement statement=connection.prepareStatement("insert into drivers values (?,?,?,?,?,?)")){
            statement.setInt(1,driver.getId());
            statement.setString(2,driver.getUserName());
            statement.setString(3,driver.getPassword());
            statement.setString(4,driver.getName());
            statement.setString(5,driver.getCarName());
            statement.setString(6,driver.getLicensePlate());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
