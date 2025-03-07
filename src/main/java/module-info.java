module com.example.pathtrekker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.sql;
    requires org.json;
    requires javafx.web;
    requires java.desktop;
    requires org.apache.pdfbox;
    opens com.example.pathtrekker to javafx.fxml;
    exports com.example.pathtrekker;
}