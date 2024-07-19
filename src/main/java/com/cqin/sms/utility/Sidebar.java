package com.cqin.sms.utility;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Sidebar {
    private final AnchorPane blockingPane;
    private final AnchorPane extendedSidePane;
    private final AnchorPane borderLeftPane;
    private boolean slidedOut;

    public Sidebar(AnchorPane blockingPane, AnchorPane extendedSidePane, AnchorPane borderLeftPane) {
        this.blockingPane = blockingPane;
        this.extendedSidePane = extendedSidePane;
        this.borderLeftPane = borderLeftPane;
        this.slidedOut = false;

        //Slides the blocking pane away when scene is loaded
        if(blockingPane != null && extendedSidePane != null) {
            blockingPane.setVisible(false);
            Sidebar.getFadeTransition(blockingPane, 0.25, 1, 0).play();
            //Sidebar.getTranslateTransition(extendedSidePane, 0.25, -600).play();
        }
    }

    public static FadeTransition getFadeTransition(Node node, double duration, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }

    public static TranslateTransition getTranslateTransition(Node node, double duration, double value) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(value);
        return translateTransition;
    }

    public void hideExtendedSideBar() {
        FadeTransition trans = getFadeTransition(blockingPane, 0.005, 0.15,0);
        trans.play();
        trans.setOnFinished(_ -> blockingPane.setVisible(false));
        borderLeftPane.setMinWidth(60);
        getTranslateTransition(extendedSidePane,0.005, -600).play();
        slidedOut = false;
    }

    private void showExtendedSideBar() {
        blockingPane.setVisible(true);
        getFadeTransition(blockingPane, 0.005, 0, 0.8).play();
        getTranslateTransition(extendedSidePane, 0.005, 600).play();
        borderLeftPane.setMinWidth(190);
        slidedOut = true;
    }

    public void toggleExtendedSideBar() {
        if(slidedOut)
            hideExtendedSideBar();
        else
            showExtendedSideBar();
    }

    public boolean isSlidedOut(){
        return slidedOut;
    }
}
