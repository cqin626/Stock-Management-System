module com.cqin.sms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires static lombok;

    opens com.cqin.sms to javafx.fxml;
    exports com.cqin.sms;
    exports com.cqin.sms.controller;
    opens com.cqin.sms.controller to javafx.fxml;
    exports com.cqin.sms.model;
    opens com.cqin.sms.model to javafx.fxml;
    exports com.cqin.sms.utility;
    opens com.cqin.sms.utility to javafx.fxml;
}