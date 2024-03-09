module com.example.labs2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.labs2 to javafx.fxml;
    exports com.example.labs2;
    exports com.example.labs2.observer;
    exports com.example.labs2.state;
    exports com.example.labs2.template;
    exports com.example.labs2.mvc;
}