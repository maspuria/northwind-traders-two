package com.northwind.model;

public class Product {

    //field members
    private int productId;
    private String ProductName;
    private int SupplierId;
    private int CategoryId;
    private String quantityPerUnit;
    private double unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private int discontinued;

    //constructor
    public Product(int productId, String productName, int supplierId,
                   int categoryId, String quantityPerUnit, double unitPrice,
                   int unitsInStock, int unitsOnOrder,
                   int reorderLevel, int discontinued) {
        this.productId = productId;
        ProductName = productName;
        SupplierId = supplierId;
        CategoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }

    //getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }

    //toString to display product data


    @Override
    public String toString() {
        return "\n Product: \n" +
                "productId=" + productId + '\n' +
                ", ProductName='" + ProductName + '\n' +
                ", SupplierId=" + SupplierId + '\n' +
                ", CategoryId=" + CategoryId + '\n' +
                ", quantityPerUnit='" + quantityPerUnit + '\n' +
                ", unitPrice=" + unitPrice + '\n' +
                ", unitsInStock=" + unitsInStock + '\n' +
                ", unitsOnOrder=" + unitsOnOrder + '\n' +
                ", reorderLevel=" + reorderLevel + '\n' +
                ", discontinued=" + discontinued + '\n';
    }
}
