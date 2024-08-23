module cr.ac.una.chatsapp {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging;
    requires MaterialFX;
    requires javafx.graphics;

    opens cr.ac.una.chatsapp to javafx.fxml;
    exports cr.ac.una.chatsapp;

    opens cr.ac.una.chatsapp.controller to javafx.fxml, MaterialFX, javafx.graphics, javafx.controls;

    
}
