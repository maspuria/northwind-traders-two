package com.northwind.data;

import com.northwind.model.Shipper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipperDao{
    private DataSource dataSource;

    public ShipperDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Shipper> getAll() {
        List<Shipper> shippers = new ArrayList<>();

        //call the database and get back products
        //create Customer objects and fill them with the data from the database ResultSet
        String query = """
                SELECT ShipperID, CompanyName, Phone
                FROM shippers;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Shipper shipper = new Shipper(
                            resultSet.getInt("ShipperID"),
                            resultSet.getString("CompanyName"),
                            resultSet.getString("Phone"));

                    shippers.add(shipper);
                }

            }
        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }

        return shippers;
    }

}