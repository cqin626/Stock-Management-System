package com.cqin.sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage pStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainView.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        pStage.initStyle(StageStyle.DECORATED);
        pStage.setScene(scene);
        pStage.setResizable(true);
        pStage.setTitle("Stock Management System");
        pStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
