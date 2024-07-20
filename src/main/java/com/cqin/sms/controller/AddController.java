package com.cqin.sms.controller;

import com.cqin.sms.service.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    private TextField productNameTxtField;
    @FXML
    private TextField productCategoryTxtField;
    @FXML
    private TextField unitPriceTxtField;
    @FXML
    private TextField remainingStockTxtField;
    @FXML
    private Button addButton, clearButton;
    ProductService productService;

    public AddController() {
        this.productService = new ProductService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Assign the addProduct feature to addButton
        addButton.setOnAction(_ -> {
            try {
                String prodName = productNameTxtField.getText();
                String prodCategory = productCategoryTxtField.getText();
                String unitPrice = unitPriceTxtField.getText();
                String remStock = remainingStockTxtField.getText();

                if(productService.addProduct(prodName, prodCategory,unitPrice,remStock)) {
                    MainController.getAlert(Alert.AlertType.INFORMATION,"Product successfully inserted to database.").showAndWait();
                } else {
                    MainController.getAlert(Alert.AlertType.ERROR,"An error has occurred. Please try again later.").showAndWait();
                }
            } catch (RuntimeException re) {
                MainController.getAlert(Alert.AlertType.ERROR, re.getMessage()).showAndWait();
            }
            clearUserInputs();
        });

        //Clean user input on the form
        clearButton.setOnAction( _ -> {
            clearUserInputs();
        });
    }

    public void clearUserInputs() {
        productNameTxtField.setText("");
        productCategoryTxtField.setText("");
        unitPriceTxtField.setText("");
        remainingStockTxtField.setText("");
    }


}
