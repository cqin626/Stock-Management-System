package com.cqin.sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    double x, y = 0;
    @Override
    public void start(Stage pStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewAndController/AddProductView.fxml"));
        Parent root = fxmlLoader.load();

        pStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(e -> {
           x = e.getSceneX();
           y = e.getSceneY();
        });

        root.setOnMouseDragged(e -> {
            pStage.setX(e.getScreenX() - x);
            pStage.setY(e.getScreenY() - y);
        });

        pStage.setScene(new Scene(root));
        pStage.setTitle("Undecorated Stage Example");
        pStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}