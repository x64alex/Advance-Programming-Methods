module com.example.toylanguage {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.toylanguage.View.gui to javafx.fxml;
    exports com.example.toylanguage.View.gui;
    exports com.example.toylanguage;
    opens com.example.toylanguage to javafx.fxml;
}