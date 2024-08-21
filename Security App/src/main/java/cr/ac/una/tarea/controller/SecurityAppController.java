/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class SecurityAppController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private MFXButton btnAdminSystems;

    @FXML
    private MFXButton btnAdminUsers;

    @FXML
    private ImageView imgViewUserPhotProf;

    @FXML
    private HBox root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

         @Override
    public void initialize() {

    }
    @FXML
    private void onActionBtnAdminUsers(ActionEvent event) {
        // Usa el FlowController para cargar el StackPane en el HBox de la vista principal
        FlowController.getInstance().goView("AdminUsersView", "Center", null);
    }

    @FXML
    void onActionBtnAdminSystems(ActionEvent event) {

    }


}
