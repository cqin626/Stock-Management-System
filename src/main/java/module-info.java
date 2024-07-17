module com.cqin.sms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.cqin.sms to javafx.fxml;
    exports com.cqin.sms;
    exports com.cqin.sms.viewAndController;
    opens com.cqin.sms.viewAndController to javafx.fxml;
    exports com.cqin.sms.model;
    opens com.cqin.sms.model to javafx.fxml;
    exports com.cqin.sms.utility;
    opens com.cqin.sms.utility to javafx.fxml;
}