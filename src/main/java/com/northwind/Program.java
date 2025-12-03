package com.northwind;

import com.northwind.data.CustomerDao;
import com.northwind.data.ProductDao;
import com.northwind.data.ShipperDao;
import com.northwind.model.Customer;
import com.northwind.model.Product;
import com.northwind.model.Shipper;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];
        String url = "jdbc:mysql://localhost:3306/northwind";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // ===================== Customer ======================
        CustomerDao customerDao = new CustomerDao(dataSource);
        List<Customer> customers = customerDao.getAll();
        System.out.println(customers);

        // ===================== Product ======================
        ProductDao productDao = new ProductDao(dataSource);
        List<Product> products = productDao.getAll();
        System.out.println(products);

        System.out.println("\n=========== Test ===========");
        System.out.println("\n==== Find Product By ID ====");
        Product foundProduct = productDao.find(78);
        if (foundProduct != null) {
            System.out.println("Product Found: " + foundProduct);
        } else {
            System.out.println("Shipper not found.");
        }

        // ===================== Shipper ======================
        ShipperDao shipperDao = new ShipperDao(dataSource);
        List<Shipper> shippers = shipperDao.getAll();
        System.out.println(shippers);

        System.out.println("\n=========== Test ===========");
        System.out.println("\n==== Find Shipper By ID ====");
        Shipper foundShipper = shipperDao.find(2);
        if (foundShipper != null) {
            System.out.println("Shipper Found: " + foundShipper);
        } else {
            System.out.println("Shipper not found.");
        }


    }
}
