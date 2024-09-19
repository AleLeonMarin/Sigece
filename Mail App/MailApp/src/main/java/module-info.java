module cr.ac.una.mailapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires jakarta.ws.rs;
    requires java.logging;
    requires jakarta.json;
    requires javassist;
    requires java.base;
    requires java.sql;
    requires jakarta.xml.bind;
    requires jakarta.json.bind;
    requires jersey.common;
    requires javafx.web;
    requires transitive javafx.graphics;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens cr.ac.una.mailapp.model;

    opens cr.ac.una.mailapp to javafx.fxml;
    exports cr.ac.una.mailapp;

    opens cr.ac.una.mailapp.controller to javafx.fxml, MaterialFX, javafx.graphics, javafx.controls;

}
