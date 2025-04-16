package ir.maktab127.repository;

import ir.maktab127.entities.Driver;

import java.sql.SQLException;
import java.util.Optional;

public interface DriverRepository {
    Optional<Driver> getByUserName(String username) throws SQLException;

    void save(Driver driver);
}
