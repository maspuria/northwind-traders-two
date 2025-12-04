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

//        // ===================== Customer ======================
//        CustomerDao customerDao = new CustomerDao(dataSource);
//        List<Customer> customers = customerDao.getAll();
//        System.out.println(customers);

        testProductCrud(dataSource);


//        // ===================== Shipper ======================
//        // Header for next section of tests
//        System.out.println("\n---------------------------------------");
//        System.out.println("            TESTING SHIPPER DAO");
//        System.out.println("---------------------------------------\n");
//
//        ShipperDao shipperDao = new ShipperDao(dataSource);
//        List<Shipper> shippers = shipperDao.getAll();
//        System.out.println(shippers);
//
//        System.out.println("\n=========== Test ===========");
//        System.out.println("\n==== Find Shipper By ID ====");
//        Shipper foundShipper = shipperDao.find(2);
//        if (foundShipper != null) {
//            System.out.println("Shipper Found: " + foundShipper);
//        } else {
//            System.out.println("Shipper not found.");
//        }


    }

    private static void testProductCrud(BasicDataSource dataSource) {
        // ===================== Product ======================

        System.out.println("\n---------------------------------------");
        System.out.println("            TESTING PRODUCT DAO");
        System.out.println("---------------------------------------\n");

        ProductDao productDao = new ProductDao(dataSource); // ProductDao object aka product database helper

        // ============================================================
        //                    TEST 1: Get All Products
        // ============================================================
        System.out.println("==== Test 1 --> Get All Products ====");

        List<Product> products = productDao.getAll();

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("\nTotal Products: " + products.size()); //get total number of products

        // ============================================================
        //                 TEST 2: Find a Specific Product
        // ============================================================
        System.out.println("\n==== Test 2 --> Find Product by ID ====");

        Product foundProduct = productDao.find(78); //Look for a product with ID 78

        if (foundProduct != null) {
            System.out.println("\nPRODUCT FOUND: " + foundProduct);
        } else {
            System.out.println("\nProduct not found.");
        }

        // ============================================================
        // TEST 3: Add a New Product
        // ============================================================
        System.out.println("\n==== Test 3 --> Add New Product ====");

        Product newProduct = new Product();

        newProduct.setProductName("Marzipan Chocolate Bar");
        newProduct.setSupplierId(30);
        newProduct.setCategoryId(3);
        newProduct.setQuantityPerUnit("10 boxes");
        newProduct.setUnitPrice(4.44);
        newProduct.setUnitsInStock(40);
        newProduct.setUnitsOnOrder(0);
        newProduct.setReorderLevel(0);
        newProduct.setDiscontinued(0);

        Product addedProduct = productDao.add(newProduct); // add product to database
        System.out.println("\nAdded: " + addedProduct);

        System.out.println("\nGenerated ID: " + addedProduct.getProductId()); // print auto-generatedID

        // ============================================================
        // TEST 4: Verify the Product Was Added
        // ============================================================
        System.out.println("\n==== Test 4 --> Verify Product Was Added ====");

        int newProductId = addedProduct.getProductId(); // save generatedID to use it later

        Product verifyProduct = productDao.find(newProductId); // try to find product using auto-generatedID

        if (verifyProduct != null) {
            System.out.println("\nVERIFIED: " + verifyProduct);
        } else {
            System.out.println("\nProduct with ID: " + newProductId + " not found after adding.");
        }

        // ============================================================
        // TEST 5: Update the Product
        // This tests the update() method to modify an existing product
        // ============================================================
        System.out.println("\n==== Test 5 --> Update the Product ====");

        if (verifyProduct != null) {
            // Modify the Products info

            verifyProduct.setProductName("Marzipan Chocolate");
            verifyProduct.setSupplierId(30);
            verifyProduct.setCategoryId(3);
            verifyProduct.setQuantityPerUnit("11 boxes");
            verifyProduct.setUnitPrice(4.44);
            verifyProduct.setUnitsInStock(45);
            verifyProduct.setUnitsOnOrder(1);
            verifyProduct.setReorderLevel(0);
            verifyProduct.setDiscontinued(0);

            // save changes
            productDao.update(verifyProduct);
            System.out.println("\nUpdated Product with ID: " + newProductId);

            //retrieve product again to view changes
            Product updatedProduct = productDao.find(newProductId);
            System.out.println("\nAfter Update: " + updatedProduct);
        }


        // ============================================================
        // TEST 6: Delete the Product
        // This tests the delete() method to remove a product
        // ============================================================
        System.out.println("\n==== Test 6 --> Delete the Product ====");

        // delete by the product's ID
        productDao.delete(newProductId);
        System.out.println("\nDeleted Product with ID: " + newProductId);

        // find product to confirm deletion
        Product deletedProduct = productDao.find(newProductId);

        if (deletedProduct == null) {
            System.out.println("\nCONFIRMED: Product with ID " + newProductId + " no longer exists!");
        } else {
            System.out.println("\nWARNING: Product with ID " + newProductId + " still exists in database after deletion");
        }


        System.out.println("\n---------------------------------------");
        System.out.println("           ALL TESTS COMPLETED");
        System.out.println("---------------------------------------\n");
    }
}
