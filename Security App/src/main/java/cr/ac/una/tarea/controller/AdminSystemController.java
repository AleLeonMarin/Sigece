/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.tarea.model.RolesDto;
import cr.ac.una.tarea.model.SistemasDto;
import cr.ac.una.tarea.service.SistemasService;
import cr.ac.una.tarea.util.Mensaje;
import cr.ac.una.tarea.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall
 */
public class AdminSystemController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAccept;

    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXButton btnNew;

    @FXML
    private StackPane root;

    @FXML
    private TableView<RolesDto> tbvRoles;

    @FXML
    private TableView<SistemasDto> tbvSistemas;

    @FXML
    private Tab tptRoles;

    @FXML
    private Tab tptSistemas;

    @FXML
    private MFXTextField txfID;

    @FXML
    private MFXTextField txfIdRol;

    @FXML
    private MFXTextField txfName;

    @FXML
    private MFXTextField txfNombreRol;

    RolesDto rol;

    SistemasDto systems;

    List<Node> requeridos = new ArrayList<>();

    @FXML
    void onActionBtnAccept(ActionEvent event) {

        try {
            String valid = validarRequeridos();
            if (!valid.isEmpty()) {
                new Mensaje().showModal(AlertType.WARNING, "Guardar", getStage(), valid);
            } else {

                SistemasService service = new SistemasService();
                Respuesta res = service.saveSystem(systems.registers());

                if (!res.getEstado()) {
                    new Mensaje().showModal(AlertType.ERROR, "Guardar Sistema", getStage(), res.getMensaje());
                } else {
                    unbindSystems();
                    this.systems = (SistemasDto) res.getResultado("Sistema");
                    bindSystems(false);
                    chargeSistems();
                    new Mensaje().showModal(AlertType.INFORMATION, "Guardar Sistema", getStage(),
                            "Sistema guardado correctamente.");

                }

            }
        } catch (Exception e) {
            Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE, "Error guardando sistema", e);
            new Mensaje().showModal(AlertType.ERROR, "Guardar Sistema", getStage(), "Error guardando el Sistema.");
        }

    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {

        try {
            if (this.systems.getId() == null) {
                new Mensaje().showModal(AlertType.WARNING, "Eliminar Sistema", getStage(),
                        "Debe seleccionar un sistema.");
            } else {

                SistemasService service = new SistemasService();
                Respuesta res = service.deleteSystem(this.systems.getId());
                if (!res.getEstado()) {
                    new Mensaje().showModal(AlertType.ERROR, "Eliminar Sistema", getStage(), res.getMensaje());
                } else {
                    new Mensaje().showModal(AlertType.INFORMATION, "Eliminar Sistema", getStage(),
                            "Sistema eliminado correctamente.");
                    chargeSistems();
                    newSystem();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE, "Error eliminando sistema", e);
            new Mensaje().showModal(AlertType.ERROR, "Eliminar Sistema", getStage(), "Error eliminando el Sistema.");
        }

    }

    @FXML
    void onActionBtnNew(ActionEvent event) {

        if (tptSistemas.isSelected()) {
            if (new Mensaje().showConfirmation("Limpiar Sistema", getStage(),
                    "¿Esta seguro que desea limpiar el registro?")) {
                newSystem();
            }
        }
        if (tptRoles.isSelected()) {
            if (new Mensaje().showConfirmation("Limpiar Rol", getStage(),
                    "¿Esta seguro que desea limpiar el registro?")) {
                newRol();
            }
        }

    }

     @FXML
    void onKeyPressedTxfIdRol(KeyEvent event) {

    }

    @FXML
    void onKeyPressedTxfIdSystema(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER && !txfID.getText().isBlank()) {
            chargeSistem(Long.valueOf(txfID.getText()));
        }

    }

    private void createColumnsSystems() {

        TableColumn<SistemasDto, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(cd -> cd.getValue().id);
        tbvSistemas.getColumns().add(colID);

        TableColumn<SistemasDto, String> colName = new TableColumn<>("Nombre");
        colName.setCellValueFactory(cd -> cd.getValue().nombre);
        tbvSistemas.getColumns().add(colName);
    }

    private void createColumnsRoles() {

        TableColumn<RolesDto, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(cd -> cd.getValue().id);
        tbvRoles.getColumns().add(colID);

        TableColumn<RolesDto, String> colName = new TableColumn<>("Nombre");
        colName.setCellValueFactory(cd -> cd.getValue().nombre);
        tbvRoles.getColumns().add(colName);
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof MFXTextField
                    && (((MFXTextField) node).getText() == null || ((MFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((MFXTextField) node).getFloatingText();
                } else {
                    invalidos += "," + ((MFXTextField) node).getFloatingText();
                }
                validos = false;
            } else if (node instanceof MFXPasswordField
                    && (((MFXPasswordField) node).getText() == null || ((MFXPasswordField) node).getText().isBlank())) {
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

    private void indicateRequiredFields() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfName));
    }

    // New methods

    private void newSystem() {

        this.systems = new SistemasDto();
        unbindSystems();
        bindSystems(true);
        txfID.clear();
        txfID.requestFocus();

    }

    private void newRol() {

        this.rol = new RolesDto();
        unbindRoles();
        bindRoles(true);
        txfIdRol.clear();
        txfIdRol.requestFocus();

    }

    // Bind methods

    private void bindSystems(Boolean isNew) {

        if (!isNew) {
            txfID.textProperty().bind(systems.id);
        }
        txfName.textProperty().bindBidirectional(systems.nombre);

    }

    private void bindRoles(Boolean isNew) {

        if (!isNew) {
            txfIdRol.textProperty().bind(this.rol.id);
        }
        txfNombreRol.textProperty().bindBidirectional(this.rol.nombre);

    }

    // Unbind methods

    private void unbindSystems() {

        txfID.textProperty().unbind();
        txfName.textProperty().unbindBidirectional(this.systems.nombre);

    }

    private void unbindRoles() {

        txfIdRol.textProperty().unbind();
        txfNombreRol.textProperty().unbindBidirectional(this.rol.nombre);

    }

    private void chargeSistems() {

        SistemasService service = new SistemasService();
        Respuesta res = service.getAll();

        if (!res.getEstado()) {
            new Mensaje().showModal(AlertType.ERROR, "Cargar Sistemas", getStage(), res.getMensaje());
        } else {
            tbvSistemas.getItems().clear();
            tbvSistemas.getItems().addAll((List<SistemasDto>) res.getResultado("Sistemas"));
        }

    }

    private void chargeSistem(Long id){

        try{
            SistemasService service = new SistemasService();
            Respuesta res = service.getSystem(id);

            if (!res.getEstado()) {
                new Mensaje().showModal(AlertType.ERROR, "Cargar Sistema", getStage(), res.getMensaje());
            } else {
                unbindSystems();
                this.systems = (SistemasDto) res.getResultado("Sistema");
                bindSystems(false);
            }
        } catch (Exception e) {
            Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE, "Error cargando sistema", e);
            new Mensaje().showModal(AlertType.ERROR, "Cargar Sistema", getStage(), "Error cargando el Sistema.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Columns creation
        createColumnsSystems();
        createColumnsRoles();

        // Ititialize the dto's

        this.rol = new RolesDto();
        this.systems = new SistemasDto();

        // New methods
        newSystem();
        newRol();

        // Requiere fields
        indicateRequiredFields();

    }

    @Override
    public void initialize() {
        chargeSistems();

    }

}
