module cr.ac.una.tarea1_progra3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cr.ac.una.tarea1_progra3 to javafx.fxml;
    exports cr.ac.una.tarea1_progra3;
}
