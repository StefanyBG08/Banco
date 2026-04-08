module com.example.banco1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.banco1 to javafx.fxml;
    exports com.example.banco1;
}