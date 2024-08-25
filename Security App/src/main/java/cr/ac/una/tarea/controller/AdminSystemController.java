/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall
 */
public class AdminSystemController extends Controller implements Initializable  {

    /**
     * Initializes the controller class.
     */

    @FXML
    private MFXButton btnAceptar;

    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXComboBox<?> choiceRol;

    @FXML
    private StackPane root;

    @FXML
    private TableView<?> tblvSystems;

    @FXML
    private TableView<?> tbvUsers;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void initialize(){}



    @FXML
    void onActionBtnAccept(ActionEvent event) {

    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {

    }


}
