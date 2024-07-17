//package com.cqin.sms.controller;
//
//import javafx.animation.FadeTransition;
//import javafx.animation.TranslateTransition;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.util.Duration;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class HelloController implements Initializable {
//    @FXML
//    private ImageView exit, menu;
//    @FXML
//    private AnchorPane pane1, pane2;
////    @FXML
////    private TableView<Product> productsTable;
////    @FXML
////    private TableColumn<Product,String> productIDCol;
////    @FXML
////    private TableColumn<Product,String> productNameCol;
////    @FXML
////    private TableColumn<Product,String> productCategoryCol;
////    @FXML
////    private TableColumn<Product,Integer> remainingStockCol;
////    @FXML
////    private TableColumn<Product,Double> unitPriceCol;
////    @FXML
////    private TableColumn<Product,String> select;
//
//    boolean slidedOut = false;
////    String query = null;
////    Connection connection = null;
////    PreparedStatement preparedStatement = null;
////    ResultSet resultSet = null;
////    Product product = null;
////    ObservableList<Product> productsList = FXCollections.observableArrayList();
//
//
//    public FadeTransition createFadeTransition(Node node, double duration, double fromValue, double toValue) {
//        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
//        fadeTransition.setFromValue(fromValue);
//        fadeTransition.setToValue(toValue);
//        return fadeTransition;
//    }
//
//    public TranslateTransition createTranslateTransition(Node node, double duration, double value) {
//        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
//        translateTransition.setByX(value);
//        return translateTransition;
//    }
//
////    public void loadData() throws SQLException {
////        connection = DbConnect.getConnection();
////        refreshTable();
////        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
////        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
////        productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
////        remainingStockCol.setCellValueFactory(new PropertyValueFactory<>("remainingStock"));
////        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
////
////    }
////
////    public void refreshTable() throws SQLException {
////        productsList.clear();
////        query = "SELECT * FROM product";
////
////        preparedStatement = connection.prepareStatement(query);
////        resultSet = preparedStatement.executeQuery();
////
////        while(resultSet.next()) {
////            productsList.add(new Product(
////               resultSet.getString("product_id"),
////               resultSet.getString("product_name"),
////               resultSet.getString("product_category"),
////               resultSet.getInt("remaining_stock"),
////                    resultSet.getDouble("unit_price")
////            ));
////            productsTable.setItems(productsList);
////        }
////    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
////        try {
////            loadData();
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
//
//        exit.setOnMouseClicked( e -> {
//            System.exit(0);
//        });
//
//        pane1.setVisible(false);
//        createFadeTransition(pane1, 0.25, 1, 0).play();
//        createTranslateTransition(pane2, 0.25, -600).play();
//
//        menu.setOnMouseClicked( e -> {
//            if(!slidedOut) {
//                pane1.setVisible(true);
//                createFadeTransition(pane1, 0.25, 0, 0.8).play();
//                createTranslateTransition(pane2, 0.25, 600).play();
//                slidedOut = true;
//            }
//            else {
//                FadeTransition trans = createFadeTransition(pane1, 0.25, 0.15,0);
//                trans.play();
//
//                trans.setOnFinished( e2 -> {
//                    pane1.setVisible(false);
//                });
//
//                createTranslateTransition(pane2,0.25, -600).play();
//                slidedOut = false;
//            }
//        });
//
//        pane1.setOnMouseClicked( e -> {
//            FadeTransition trans = createFadeTransition(pane1, 0.25, 0.15,0);
//            trans.play();
//
//            trans.setOnFinished( e2 -> {
//                pane1.setVisible(false);
//            });
//
//            createTranslateTransition(pane2,0.25, -600).play();
//        });
//    }
//
//}