module com.example.vocabularyapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    // requires eu.hansolo.tilesfx;

    opens com.example.vocabularyapp to javafx.fxml;
    exports com.example.vocabularyapp;
}