module cr.ac.una.mailapp {
      requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging;
    requires MaterialFX;
    requires javafx.graphics;

    opens cr.ac.una.mailapp to javafx.fxml;
    exports cr.ac.una.mailapp;

    opens cr.ac.una.mailapp.controller to javafx.fxml, MaterialFX, javafx.graphics, javafx.controls;

}
