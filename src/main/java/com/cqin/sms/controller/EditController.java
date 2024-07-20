package com.cqin.sms.controller;

import com.cqin.sms.model.Product;
import com.cqin.sms.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    private TextField productNameTxtField;
    @FXML
    private TextField productCategoryTxtField;
    @FXML
    private TextField unitPriceTxtField;
    @FXML
    private TextField remainingStockTxtField;
    @FXML
    private TextField searchbar;
    @FXML
    private Button updateButton, clearButton;
    @FXML
    private ImageView search;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product,String> productIDCol;
    @FXML
    private TableColumn<Product,String> productNameCol;

    ObservableList<Product> productsList = FXCollections.observableArrayList();
    ProductService productService;

    //Should disable update button initially

    public EditController() {
        this.productService = new ProductService();
    }

    // Create an event handler to store the search feature
    EventHandler<MouseEvent> searchFeature = _ -> {
        String prodID = searchbar.getText();
        try {
            Product product = productService.getProductByID(prodID);
            if (product.getProductID() != -1) {
                productNameTxtField.setText(product.getProductName());
                productCategoryTxtField.setText(product.getProductCategory());
                unitPriceTxtField.setText(Double.toString(product.getUnitPrice()));
                remainingStockTxtField.setText(Integer.toString(product.getRemainingStock()));

                // Disable the search feature by removing the event handler
                search.setOnMouseClicked(null);
                search.setOpacity(0.5);
                searchbar.setDisable(true);
                updateButton.setDisable(false);
            } else {
                MainController.getAlert(Alert.AlertType.ERROR, "Product ID not found.").showAndWait();
            }
        } catch (RuntimeException re) {
            MainController.getAlert(Alert.AlertType.ERROR, "Invalid product ID.").showAndWait();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Load products
        try{
            initializeTable();
        } catch (RuntimeException re) {
            MainController.getAlert(Alert.AlertType.ERROR,re.getMessage()).showAndWait();
        }

        // Enable search
        search.setOnMouseClicked(searchFeature);

        //Define update feature
        updateButton.setOnAction(_ ->{
            try {
                String prodID = searchbar.getText();
                String prodName = productNameTxtField.getText();
                String prodCategory = productCategoryTxtField.getText();
                String unitPrice = unitPriceTxtField.getText();
                String remStock = remainingStockTxtField.getText();

                if(productService.updateProduct(prodID, prodName, prodCategory,unitPrice,remStock)) {
                    MainController.getAlert(Alert.AlertType.INFORMATION,"Changes on product are updated to database.").showAndWait();
                } else {
                    MainController.getAlert(Alert.AlertType.ERROR,"An error has occurred. Please try again later.").showAndWait();
                }
            } catch (RuntimeException re) {
                MainController.getAlert(Alert.AlertType.ERROR, re.getMessage()).showAndWait();
            }
            clearUserInputs();
        });

        //Display the corresponding product details on the left pane when a particular row is selected
        productsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String prodID = Integer.toString(newValue.getProductID());
                Product product = productService.getProductByID(prodID);

                //Display the product information on text field for modification
                searchbar.setText(prodID);
                productNameTxtField.setText(product.getProductName());
                productCategoryTxtField.setText(product.getProductCategory());
                unitPriceTxtField.setText(Double.toString(product.getUnitPrice()));
                remainingStockTxtField.setText(Integer.toString(product.getRemainingStock()));

                // Disable the search feature by removing the event handler
                search.setOnMouseClicked(null);
                search.setOpacity(0.5);
                searchbar.setDisable(true);
                updateButton.setDisable(false);
            }
        });

        //Disable update feature when nothing is searched
        updateButton.setDisable(true);

        //Enable clear feature
        clearButton.setOnAction(_->clearUserInputs());
    }

    private void initializeTable() {
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productsList.addAll(productService.getProducts());
        productsTable.setItems(productsList);
    }

    public void clearUserInputs() {
        //Enable back the searchbar and searchButton once form is cleared
        productNameTxtField.setText("");
        productCategoryTxtField.setText("");
        unitPriceTxtField.setText("");
        remainingStockTxtField.setText("");
        searchbar.setText("");
        searchbar.setDisable(false);
        search.setOnMouseClicked(searchFeature);
        search.setOpacity(1);
        //Disable the update feature when form is cleared
        updateButton.setDisable(true);
    }
}
