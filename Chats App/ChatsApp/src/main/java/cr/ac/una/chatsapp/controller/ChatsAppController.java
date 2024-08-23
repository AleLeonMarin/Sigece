/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.chatsapp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.chatsapp.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class ChatsAppController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
    }


    @FXML
    private MFXButton btnChangePass;

    @FXML
    private MFXButton btnLogIn;

    @FXML
    private MFXButton btnRegister;

    @FXML
    private MFXTextField textMail;

    @FXML
    private MFXTextField textPassword;

    @FXML
    void onActionBtnChangePass(ActionEvent event) {

        FlowController.getInstance().goViewInWindow("KeyAcceptView");

    }

    @FXML
    void onActionBtnLogIn(ActionEvent event) {

        FlowController.getInstance().goMain("SecurityAppView");

    }

    @FXML
    void onActionBtnRegister(ActionEvent event) {

        FlowController.getInstance().goViewInWindow("RegisterView");

    }

}
