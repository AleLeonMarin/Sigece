/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall
 */
public class AdminSystemController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAceptarRol;

    @FXML
    private MFXButton btnAceptarSistenas;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnDeleteRol;

    @FXML
    private MFXButton btnDeleteSistemas;

    @FXML
    private MFXButton btnNuevoRol;

    @FXML
    private MFXButton btnNuevoSistema;

    @FXML
    private StackPane root;

    @FXML
    private TableView<?> tbvRoles;

    @FXML
    private TableView<?> tbvSistemas;

    @FXML
    private TableView<?> tbvUsers;

    @FXML
    private Tab tptInclusion;

    @FXML
    private Tab tptRoles;

    @FXML
    private Tab tptSistemas;

    @FXML
    private MFXTextField txfID;

    @FXML
    private MFXTextField txfIdRol;

    @FXML
    private MFXTextField txfIdUser;

    @FXML
    private MFXTextField txfName;

    @FXML
    private MFXTextField txfNombreRol;

    @FXML
    private MFXTextField txfNombreUser;

    @FXML
    void onActionBtnAcceptRol(ActionEvent event) {

    }

    @FXML
    void onActionBtnAcceptSistema(ActionEvent event) {

    }

    @FXML
    void onActionBtnAddUser(ActionEvent event) {

    }

    @FXML
    void onActionBtnDeleteRol(ActionEvent event) {

    }

    @FXML
    void onActionBtnDeleteSistema(ActionEvent event) {

    }

    @FXML
    void onActionBtnNuevoRol(ActionEvent event) {

    }

    @FXML
    void onActionBtnNuevoSistema(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub

    }

}
