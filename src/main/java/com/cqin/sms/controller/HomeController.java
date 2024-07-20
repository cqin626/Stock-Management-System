package com.cqin.sms.controller;

import com.cqin.sms.model.Product;
import com.cqin.sms.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
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
    @FXML
    private TableColumn<Product,CheckBox> selectCol;
    @FXML
    private ImageView delete, refresh;
    @FXML
    private TextField searchbar;
    ObservableList<Product> productsList = FXCollections.observableArrayList();
    FilteredList<Product> filteredList = new FilteredList<>(productsList, p -> true);
    ProductService productService;

    public HomeController() {
        this.productService = new ProductService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Render the products on table
        try {
            initializeTable();
        } catch (RuntimeException re) {
            MainController.getAlert(Alert.AlertType.ERROR, re.getMessage()).showAndWait();
        }

        //Disable the select feature of rows
        productsTable.setSelectionModel(null);

        //Define the delete feature, can be improved by implementing lister on checkbox, no need to loop through the list every time
        delete.setOnMouseClicked( _ -> {
            List<Integer> productsToDelete = new ArrayList<>();
            for(Product product : productsList) {
                if(product.getCheckBox().isSelected()) {
                    productsToDelete.add(product.getProductID());
                }
            }
            if(productsToDelete.isEmpty())
                MainController.getAlert(Alert.AlertType.ERROR, "No product is selected.").showAndWait();
            else{
                try{
                    if(productService.deleteProducts(productsToDelete)) {
                        MainController.getAlert(Alert.AlertType.INFORMATION,"Selected products have been removed.").showAndWait();
                        refreshTable();
                    }
                } catch(RuntimeException re){
                    MainController.getAlert(Alert.AlertType.ERROR, re.getMessage()).showAndWait();
                }
            }
        });

        //Enable search feature
        searchbar.textProperty().addListener((obs, oldText, newText) -> {
            filteredList.setPredicate(product -> {
                if (newText == null || newText.isEmpty()) {
                    return true; // Show all products if search field is empty
                }
                try {
                    return Integer.toString(product.getProductID()).startsWith(newText);
                } catch (NumberFormatException e) {
                    return false; // If input is not a valid number, do not show any products
                }
            });
        });


        //Define the refresh feature
        refresh.setOnMouseClicked(_ -> {
            refreshTable();
        });
    }


    private void initializeTable() {
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        remainingStockCol.setCellValueFactory(new PropertyValueFactory<>("remainingStock"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        productsList.addAll(productService.getProducts());
        productsTable.setItems(filteredList);
    }

    public void refreshTable() {
        productsList.clear();
        productsList.addAll(productService.getProducts());
        productsTable.setItems(filteredList);
    }
}
