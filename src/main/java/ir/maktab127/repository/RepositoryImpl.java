package ir.maktab127.repository;

import java.sql.Connection;

public class RepositoryImpl implements Repository{
    protected Connection connection;
    public RepositoryImpl(Connection connection)  {
        this.connection=connection;



    }
}
