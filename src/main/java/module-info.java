module com.example.pathtrekker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.example.pathtrekker to javafx.fxml;
    exports com.example.pathtrekker;
}