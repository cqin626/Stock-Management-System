//package com.cqin.sms.controller;
//
//import com.cqin.sms.model.Product;
//import com.cqin.sms.service.ProductService;
//import com.cqin.sms.utility.Sidebar;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class HomeController extends BaseController implements Initializable {
//    @FXML
//    private ImageView exit, menu;
//    @FXML
//    private AnchorPane blockingPane, extendedSidePane;
//    @FXML
//    private TableView<Product> productsTable;
//    @FXML
//    private TableColumn<Product,String> productIDCol;
//    @FXML
//    private TableColumn<Product,String> productNameCol;
//    @FXML
//    private TableColumn<Product,String> productCategoryCol;
//    @FXML
//    private TableColumn<Product,Integer> remainingStockCol;
//    @FXML
//    private TableColumn<Product,Double> unitPriceCol;
////    @FXML
////    private TableColumn<Product,String> selectCol;
//    @FXML
//    private Button addProductButton;
//    ObservableList<Product> productsList = FXCollections.observableArrayList();
//    ProductService productService;
//    Sidebar sidebar;
//
//    public HomeController() {
//        this.productService = new ProductService();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        this.sidebar =  new Sidebar(blockingPane, extendedSidePane, );
//
//        //Gives home icon the ability to switch to home screen
//        addProductButton.setOnAction(e->{
//            try {
//                switchToScene(e, "view/AddProductView.fxml");
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//
//        //Gives menu icon the ability to toggle side bar
//        menu.setOnMouseClicked( e -> sidebar.toggleExtendedSideBar());
//
//        //Hides the extended sidebar when blocking pane is clicked
//        blockingPane.setOnMouseClicked( e -> sidebar.hideExtendedSideBar());
//
//        //Render the products on table
//        initializeTable();
//
//        //Exits the program when the exit icon is clicked
//        exit.setOnMouseClicked( e -> System.exit(0));
//
//    }
//
//    private void initializeTable() {
//        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
//        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
//        productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
//        remainingStockCol.setCellValueFactory(new PropertyValueFactory<>("remainingStock"));
//        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
//
////        // Use Callback to handle checkbox column
////        selectCol.setCellValueFactory(cellData -> {
////            CheckBox checkBox = new CheckBox();
////            BooleanProperty property = cellData.getValue().remarkProperty();
////            checkBox.selectedProperty().bindBidirectional(property);
////            return new SimpleObjectProperty<>(checkBox).asString();
////        });
//
//        loadProducts();
//    }
//
//    private void loadProducts() {
//        productsList.addAll(productService.getProducts());
//        productsTable.setItems(productsList);
//    }
//}
