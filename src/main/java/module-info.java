module com.cqin.sms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.cqin.sms to javafx.fxml;
    exports com.cqin.sms;
}