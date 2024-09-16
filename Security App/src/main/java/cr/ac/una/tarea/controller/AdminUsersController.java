package cr.ac.una.tarea.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import cr.ac.una.tarea.model.UsuariosDto;
import cr.ac.una.tarea.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AdminUsersController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXButton btnNew;

    @FXML
    private MFXButton btnSave;

    @FXML
    private MFXComboBox<String> cmbLan;

    @FXML
    private ImageView imgViewUser;

    @FXML
    private StackPane root;

    @FXML
    private MFXTextField txfCed;

    @FXML
    private MFXTextField txfCel;

    @FXML
    private MFXTextField txfID;

    @FXML
    private MFXTextField txfLasts;

    @FXML
    private MFXTextField txfMail;

    @FXML
    private MFXTextField txfNombre;

    @FXML
    private MFXTextField txfPassword;

    @FXML
    private MFXTextField txfStatus;

    @FXML
    private MFXTextField txfTel;

    @FXML
    private MFXTextField txfUser;

    @FXML
    private TableView<?> tbvUsers;

    UsuariosDto usuariosDto;

    List<Node> requeridos = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        usuariosDto = new UsuariosDto();
        newUser();
        indicateRequiredFields();

    }

    private void bindUser(boolean newUser) {
        if (!newUser) {
            txfID.textProperty().bind(usuariosDto.id);
        }
        txfNombre.textProperty().bindBidirectional(usuariosDto.nombre);
        txfLasts.textProperty().bindBidirectional(usuariosDto.apellidos);
        txfCed.textProperty().bindBidirectional(usuariosDto.cedula);
        txfMail.textProperty().bindBidirectional(usuariosDto.correo);
        txfTel.textProperty().bindBidirectional(usuariosDto.telefono);
        txfCel.textProperty().bindBidirectional(usuariosDto.celular);
        cmbLan.valueProperty().bindBidirectional(usuariosDto.idioma);
        txfUser.textProperty().bindBidirectional(usuariosDto.usuario);
        txfPassword.textProperty().bindBidirectional(usuariosDto.clave);
        txfStatus.textProperty().bindBidirectional(usuariosDto.status);
        imgViewUser.setImage(byteToImage(usuariosDto.foto));
    }

    public void unbindUser(){
        txfID.textProperty().unbind();
        txfNombre.textProperty().unbindBidirectional(usuariosDto.nombre);
       txfLasts.textProperty().unbindBidirectional(usuariosDto.apellidos);
        txfCed.textProperty().unbindBidirectional(usuariosDto.cedula);
        txfMail.textProperty().unbindBidirectional(usuariosDto.correo);
        txfTel.textProperty().unbindBidirectional(usuariosDto.telefono);
        txfCel.textProperty().unbindBidirectional(usuariosDto.celular);
        cmbLan.valueProperty().unbindBidirectional(usuariosDto.idioma);
        txfUser.textProperty().unbindBidirectional(usuariosDto.usuario);
        txfPassword.textProperty().unbindBidirectional(usuariosDto.clave);
        txfStatus.textProperty().unbindBidirectional(usuariosDto.status);
        imgViewUser.setImage(null);
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof MFXTextField && (((MFXTextField) node).getText()==null || ((MFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((MFXTextField) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXTextField) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXPasswordField && (((MFXPasswordField) node).getText()==null || ((MFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((MFXPasswordField) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXPasswordField) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXDatePicker && ((MFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((MFXDatePicker) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXDatePicker) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXComboBox && ((MFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((MFXComboBox) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXComboBox) node).getFloatingText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    private void newUser(){
        unbindUser();
        usuariosDto = new UsuariosDto();
        bindUser(true);
        txfID.clear();
        txfID.requestFocus();
    }

    private void indicateRequiredFields() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfNombre, txfLasts, txfCed, txfMail, txfTel, txfCel, cmbLan, txfUser, txfPassword, txfStatus));
    }

    @Override
    public void initialize() {
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {

    }

    @FXML
    void onActionBtnNew(ActionEvent event) {

    }

    @FXML
    void onActionBtnSave(ActionEvent event) {

    }

    @FXML
    void onActionImgvPhoto(MouseEvent event) {

        FlowController.getInstance().goViewInWindow("CamView");

    }

    private Image byteToImage(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new Image(bis);
    }

}
