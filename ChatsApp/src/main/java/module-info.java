module cr.ac.una.chatsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires jakarta.ws.rs;
    requires java.logging;
    requires jakarta.json;
    //requires javassist;
    requires java.base;
    requires java.sql;
    requires jakarta.xml.bind;
    requires jakarta.json.bind;
    requires jersey.common;
    requires transitive javafx.graphics;
    requires jakarta.xml.soap;
    requires jakarta.xml.ws;
    requires javafx.swing;
    requires javafx.base;
    requires webcam.capture;

    opens cr.ac.una.chatsapp.model;


    exports cr.ac.una.chatsapp;
    opens cr.ac.una.chatsapp to javafx.fxml;

    opens cr.ac.una.chatsapp.controller to javafx.fxml, MaterialFX, javafx.graphics, javafx.controls;
    opens cr.ac.una.securityws.controller;
}
