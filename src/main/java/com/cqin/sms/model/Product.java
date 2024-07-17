package com.cqin.sms.model;

public class Product {
    private final String productID;
    private String productName;
    private String productCategory;
    private int remainingStock;
    private double unitPrice;

    public Product(String productID, String productName, String productCategory, int remainingStock, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.remainingStock = remainingStock;
        this.unitPrice = unitPrice;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(int remainingStock) {
        this.remainingStock = remainingStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", remainingStock=" + remainingStock +
                ", unitPrice=" + unitPrice +
                '}';
    }


}
