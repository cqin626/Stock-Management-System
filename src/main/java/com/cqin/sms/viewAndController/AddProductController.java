package com.cqin.sms.viewAndController;

import com.cqin.sms.service.ProductService;
import com.cqin.sms.utility.Sidebar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController extends BaseController implements Initializable {
    @FXML
    private AnchorPane blockingPane, extendedSidePane;
    @FXML
    private ImageView exit, menu;
    @FXML
    private TextField productIDTxtField;
    @FXML
    private TextField productNameTxtField;
    @FXML
    private TextField productCategoryTxtField;
    @FXML
    private TextField unitPriceTxtField;
    @FXML
    private Spinner<Integer> remainingStockSpinner;
    @FXML
    private Button addButton, clearButton, homeButton;

    ProductService productService;
    Sidebar sidebar;
    SpinnerValueFactory<Integer> valueFactory;

    public AddProductController() {
        this.productService = new ProductService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.sidebar =  new Sidebar(blockingPane, extendedSidePane);
        valueFactory = null;

        //Initialize spinner for remaining stock
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999);
        valueFactory.setValue(0);
        remainingStockSpinner.setValueFactory(valueFactory);

        //Gives home icon the ability to switch to home screen
        homeButton.setOnAction(e->{
            try {
                switchToScene(e, "viewAndController/HomeView.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //Gives menu icon the ability to toggle side bar
        menu.setOnMouseClicked( e -> {
            sidebar.toggleExtendedSideBar();
        });

        //Hides the extended sidebar when blocking pane is clicked
        blockingPane.setOnMouseClicked( e -> {
            sidebar.hideExtendedSideBar();
        });

        //Assign the addProduct feature to addButton
        addButton.setOnAction( e-> {
            String prodId = productIDTxtField.getText();
            String prodName = productNameTxtField.getText();
            String prodCategory = productCategoryTxtField.getText();
            String unitPrice = unitPriceTxtField.getText();
            int remStock = remainingStockSpinner.getValue();

            if(productService.addProduct(prodId, prodName, prodCategory, unitPrice, remStock)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful Insert");
                alert.setContentText("Given product is successfully inserted into the database.");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to Insert");
                alert.setContentText("An error has occurred. Try reenter the information or restart the system");
                alert.showAndWait();
            }
            clearUserInputs();
        });

        clearButton.setOnAction( e -> {
            clearUserInputs();
        });

        //Exits the program when the exit icon is clicked
        exit.setOnMouseClicked( e -> {
            System.exit(0);
        });
    }

    public void clearUserInputs() {
        productIDTxtField.setText("");
        productNameTxtField.setText("");
        productCategoryTxtField.setText("");
        unitPriceTxtField.setText("");
        valueFactory.setValue(0);
        remainingStockSpinner.setValueFactory(valueFactory);
    }
}
