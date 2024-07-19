package com.cqin.sms.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.awt.*;

public class Product {
    private int productID;
    private String productName;
    private String productCategory;
    private int remainingStock;
    private double unitPrice;
//    private BooleanProperty remark;

    public Product() {
        productID = -1;
    }

    public Product(int productID, String productName, String productCategory, int remainingStock, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.remainingStock = remainingStock;
        this.unitPrice = unitPrice;
        //this.remark = new SimpleBooleanProperty(false); // Initialize with false (unchecked)
    }

    public Product(String productName, String productCategory, int remainingStock, double unitPrice) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.remainingStock = remainingStock;
        this.unitPrice = unitPrice;
    }


    public int getProductID() {
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

//    public BooleanProperty remarkProperty() {
//        return remark;
//    }
//
//    public void setRemark(boolean remark) {
//        this.remark.set(remark);
//    }

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
