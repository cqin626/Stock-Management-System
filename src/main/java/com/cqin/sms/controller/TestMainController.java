package com.cqin.sms.controller;

import com.cqin.sms.Main;
import com.cqin.sms.utility.Sidebar;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class TestMainController implements Initializable {

    @FXML
    private ImageView menu, goToHomeImgView, goToAddImgView, goToEditImgView;
    @FXML
    private AnchorPane blockingPane, extendedSidePane, contentPane, borderLeftPane;
    @FXML
    private Button goToHomeButton, goToAddButton, goToEditButton;
    private Sidebar sidebar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Initialize sidebar
        this.sidebar =  new Sidebar(blockingPane, extendedSidePane, borderLeftPane);

        //Load home view into content pane
        try {
            loadPane("view/TestHomeView.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Gives menu icon the ability to toggle sidebar
        menu.setOnMouseClicked(_ -> sidebar.toggleExtendedSideBar());

        //Hides the extended sidebar when blocking pane is clicked
        blockingPane.setOnMouseClicked(_ -> sidebar.hideExtendedSideBar());

        //Initialize the navigation feature of icons
        goToHomeImgView.setOnMouseClicked( _ -> {
            try {
                loadPane("view/TestHomeView.fxml");
                if(sidebar.isSlidedOut()) sidebar.hideExtendedSideBar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        goToAddImgView.setOnMouseClicked( _ -> {
            try {
                loadPane("view/TestAddView.fxml");
                if(sidebar.isSlidedOut()) sidebar.hideExtendedSideBar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        goToEditImgView.setOnMouseClicked( _ -> {
            try {
                loadPane("view/TestEditView.fxml");
                if(sidebar.isSlidedOut()) sidebar.hideExtendedSideBar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //Initialize the navigation feature of buttons
        goToHomeButton.setOnAction( _ -> {
            try {
                loadPane("view/TestHomeView.fxml");
                sidebar.hideExtendedSideBar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        goToAddButton.setOnAction( _ -> {
            try {
                loadPane("view/TestAddView.fxml");
                sidebar.hideExtendedSideBar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        goToEditButton.setOnAction( _ -> {
            try {
                loadPane("view/TestEditView.fxml");
                sidebar.hideExtendedSideBar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public AnchorPane getNewContent(String destination) throws IOException {
        //Return the loaded fxml file as AnchorPane
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(destination));
        return fxmlLoader.load();
    }

    public void loadPane(String FXMLFile) throws IOException {
        AnchorPane newContent = getNewContent(FXMLFile);

        //Ensure that newly loaded pane fits to its parent's size
        AnchorPane.setTopAnchor(newContent, 0.0);
        AnchorPane.setBottomAnchor(newContent, 0.0);
        AnchorPane.setLeftAnchor(newContent, 0.0);
        AnchorPane.setRightAnchor(newContent, 0.0);

        contentPane.getChildren().clear();
        contentPane.getChildren().add(newContent);
    }

    public static Alert getAlert(Alert.AlertType alertType, String msg) {
        Alert alert = new Alert(alertType);
        alert.setContentText(msg);
        return alert;
    }
}
