package com.northwind.data;

import com.northwind.model.Product;
import com.northwind.model.Shipper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        String query = """
                SELECT ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued
                FROM products;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("ProductID"),
                            resultSet.getString("ProductName"),
                            resultSet.getInt("SupplierID"),
                            resultSet.getInt("CategoryID"),
                            resultSet.getString("QuantityPerUnit"),
                            resultSet.getDouble("UnitPrice"),
                            resultSet.getInt("UnitsInStock"),
                            resultSet.getInt("UnitsOnOrder"),
                            resultSet.getInt("ReorderLevel"),
                            resultSet.getInt("Discontinued"));

                    products.add(product);
                }

            }
        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }

        return products;
    }

    public Product find(int productId) {

        Product product = null;

        String query = """
                SELECT ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued
                FROM products
                WHERE ProductID = ?;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("ProductID"),
                            resultSet.getString("ProductName"),
                            resultSet.getInt("SupplierID"),
                            resultSet.getInt("CategoryID"),
                            resultSet.getString("QuantityPerUnit"),
                            resultSet.getDouble("UnitPrice"),
                            resultSet.getInt("UnitsInStock"),
                            resultSet.getInt("UnitsOnOrder"),
                            resultSet.getInt("ReorderLevel"),
                            resultSet.getInt("Discontinued"));
                }
            }
        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }
        return product;
    }

    // update method to update an existing customer
    public void update(Product product) {

        String query = """
                UPDATE products
                SET ProductID = ?,
                    ProductName = ?,
                    SupplierID = ?,
                    CategoryID = ?,
                    QuantityPerUnit = ?,
                    UnitPrice = ?,
                    UnitsInStock = ?,
                    UnitsOnOrder = ?,
                    ReorderLevel = ?,
                    Discontinued = ?
                WHERE ProductID = ?;
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setInt(3,product.getSupplierId());
            statement.setInt(4, product.getCategoryId());
            statement.setString(5, product.getQuantityPerUnit());
            statement.setDouble(6,product.getUnitPrice());
            statement.setInt(7,product.getUnitsInStock());
            statement.setInt(8, product.getUnitsOnOrder());
            statement.setInt(9,product.getReorderLevel());
            statement.setInt(10, product.getDiscontinued());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error updating the customer. Please try again.");
            e.printStackTrace();
        }
    }

    // delete method to delete customer
    public void delete(String productId) {

        String query = """
                DELETE FROM products
                WHERE ProductID = ?;
                """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(query));



    }


}
