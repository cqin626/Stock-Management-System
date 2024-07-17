package com.cqin.sms.viewAndController;

import com.cqin.sms.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BaseController {

    public void switchToScene(ActionEvent e, String destination) throws IOException {
        Stage stage = null;
        Scene scene = null;
        Parent root = null;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(destination));
        root = fxmlLoader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
