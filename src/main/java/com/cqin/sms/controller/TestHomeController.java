package com.cqin.sms.controller;

import com.cqin.sms.model.Product;
import com.cqin.sms.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class TestHomeController implements Initializable {
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product,String> productIDCol;
    @FXML
    private TableColumn<Product,String> productNameCol;
    @FXML
    private TableColumn<Product,String> productCategoryCol;
    @FXML
    private TableColumn<Product,Integer> remainingStockCol;
    @FXML
    private TableColumn<Product,Double> unitPriceCol;


    ObservableList<Product> productsList = FXCollections.observableArrayList();
    ProductService productService;

    public TestHomeController() {
        this.productService = new ProductService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Render the products on table
        try{
            initializeTable();
        } catch (RuntimeException re) {
            TestMainController.getAlert(Alert.AlertType.ERROR,re.getMessage()).showAndWait();
        }

    }

    private void initializeTable() {
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        remainingStockCol.setCellValueFactory(new PropertyValueFactory<>("remainingStock"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        productsList.addAll(productService.getProducts());
        productsTable.setItems(productsList);
    }


}
