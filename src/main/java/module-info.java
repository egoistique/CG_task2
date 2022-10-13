module com.example.t2fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.t2fx to javafx.fxml;
    exports com.example.t2fx;
}