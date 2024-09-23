/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class MailAppController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

      @FXML
    private MFXButton btnAdminParameters;

    @FXML
    private MFXButton btnAdminProcess;

    @FXML
    private MFXButton btnLogOut;

    @FXML
    private MFXButton btnMailBox;

    @FXML
    private MFXButton btnMassiveMails;

    @FXML
    private Button btnNewMail;

    @FXML
    private ImageView imgViewUserPhotProf;

    @FXML
    private StackPane stackPaneRoot;

    @FXML
    private HBox root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize(){
    }

    @FXML
    void onActionBtnAdminParameters(ActionEvent event) {

        FlowController.getInstance().goView("AdminParametersView", null);

    }

    @FXML
    void onActionBtnAdminProcess(ActionEvent event) {

        FlowController.getInstance().goView("AdminNotificationView");

    }

    @FXML
    void onActionBtnMassiveMails(ActionEvent event) {

        FlowController.getInstance().goView("MassiveMailSenderView");

    }

    @FXML
    void onBtnMailBox(ActionEvent event) {

        FlowController.getInstance().goView("BuzonView");

    }


    @FXML
    void onActionBtnNewMail(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("EnvioCorreoView", this.getStage(), Boolean.FALSE);

    }



}