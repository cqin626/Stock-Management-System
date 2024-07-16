package com.cqin.sms;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView exit, menu;

    @FXML
    private AnchorPane pane1, pane2;

    boolean slidedOut = false;

    public FadeTransition createFadeTransition(Node node, double duration, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }

    public TranslateTransition createTranslateTransition(Node node, double duration, double value) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(value);
        return translateTransition;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked( e -> {
            System.exit(0);
        });

        pane1.setVisible(false);
        createFadeTransition(pane1, 0.25, 1, 0).play();
        createTranslateTransition(pane2, 0.25, -600).play();

        menu.setOnMouseClicked( e -> {
            if(!slidedOut) {
                pane1.setVisible(true);
                createFadeTransition(pane1, 0.25, 0, 0.8).play();
                createTranslateTransition(pane2, 0.25, 600).play();
                slidedOut = true;
            }
            else {
                FadeTransition trans = createFadeTransition(pane1, 0.25, 0.15,0);
                trans.play();

                trans.setOnFinished( e2 -> {
                    pane1.setVisible(false);
                });

                createTranslateTransition(pane2,0.25, -600).play();
                slidedOut = false;
            }
        });

        pane1.setOnMouseClicked( e -> {
            FadeTransition trans = createFadeTransition(pane1, 0.25, 0.15,0);
            trans.play();

            trans.setOnFinished( e2 -> {
                pane1.setVisible(false);
            });

            createTranslateTransition(pane2,0.25, -600).play();
        });
    }

}