module cr.ac.una.chatsapp {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires javafx.graphics;
    requires jakarta.ws.rs;
    requires java.logging;
    requires jakarta.json;

    opens cr.ac.una.chatsapp to javafx.fxml;
    exports cr.ac.una.chatsapp;

    opens cr.ac.una.chatsapp.controller to javafx.fxml, MaterialFX, javafx.graphics, javafx.controls;

    
}
