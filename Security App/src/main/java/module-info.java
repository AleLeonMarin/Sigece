module cr.ac.una.tarea {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging;
    requires jakarta.xml.bind;
    requires jakarta.xml.soap;
    requires jakarta.xml.ws;
    requires MaterialFX;
    requires transitive javafx.graphics;

    opens cr.ac.una.tarea to javafx.fxml;
    exports cr.ac.una.tarea;

    opens cr.ac.una.tarea.controller to javafx.fxml, MaterialFX, javafx.graphics, javafx.controls;
    exports cr.ac.una.tarea.controller;
}
