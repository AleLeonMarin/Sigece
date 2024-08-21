/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class RegisterController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private MFXButton btnAccept;

    @FXML
    private MFXButton btnGoBack;

    @FXML
    private ChoiceBox<?> choiceBoxLanguage;

    @FXML
    private ImageView imgViewUserPhoto;

    @FXML
    private StackPane root;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @Override
    public void initialize() {
        // TODO Auto-generated method stub

    }



    @FXML
    void onActionBtnAccept(ActionEvent event) {

    }

    @FXML
    void onActionBtnGoback(ActionEvent event) {

    }

    @FXML
    void onActionUserPhoto(MouseEvent event) {

    }
}
