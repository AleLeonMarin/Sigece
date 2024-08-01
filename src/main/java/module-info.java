module tarea {

    requires java.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires MaterialFX;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires jakarta.persistence;
    requires jakarta.xml.bind;
    requires eclipselink;
    requires java.logging;

    exports cr.ac.una.tarea;
    opens cr.ac.una.tarea to javafx.fxml;
    

    
}
