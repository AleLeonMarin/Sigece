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
import cr.ac.una.tarea.service.RolesService;
import cr.ac.una.tarea.service.SistemasService;
import cr.ac.una.tarea.util.Mensaje;
import cr.ac.una.tarea.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private TabPane tpbSistemas;

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

        if (tptSistemas.isSelected()) {
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
                                "Sistema y roles guardados correctamente.");
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE,
                        "Error guardando sistema y roles",
                        e);
                new Mensaje().showModal(AlertType.ERROR, "Guardar Sistema", getStage(),
                        "Error guardando el Sistema y roles.");
            }
        }
        if (tptRoles.isSelected()) {

            try {
                String valid = validarRequeridos();
                if (!valid.isEmpty()) {
                    new Mensaje().showModal(AlertType.WARNING, "Guardar", getStage(), valid);
                } else {
                    rol.setSistema(systems);
                    RolesService service = new RolesService();
                    Respuesta res = service.saveRol(rol.register());

                    if (!res.getEstado()) {
                        new Mensaje().showModal(AlertType.ERROR, "Guardar Rol", getStage(), res.getMensaje());
                    } else {
                        unbindRoles();
                        this.rol = (RolesDto) res.getResultado("Rol");
                        bindRoles(false);
                        chargeSistems();
                        new Mensaje().showModal(AlertType.INFORMATION, "Guardar Rol", getStage(),
                                "Rol guardados correctamente.");
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE,
                        "Error guardando el rol",
                        e);
                new Mensaje().showModal(AlertType.ERROR, "Guardar Rol", getStage(),
                        "Error guardando el rol");
            }

        }
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {

        if (tptSistemas.isSelected()) {
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
                Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE, "Error eliminando sistema",
                        e);
                new Mensaje().showModal(AlertType.ERROR, "Eliminar Sistema", getStage(),
                        "Error eliminando el Sistema.");
            }
        }
        if (tptRoles.isSelected()) {

            try {
                if (this.rol.getId() == null) {
                    new Mensaje().showModal(AlertType.WARNING, "Eliminar Rol", getStage(),
                            "Debe seleccionar un Rol.");
                } else {

                    RolesService service = new RolesService();
                    Respuesta res = service.deleteRole(this.rol.getId());
                    if (!res.getEstado()) {
                        new Mensaje().showModal(AlertType.ERROR, "Eliminar Rol", getStage(), res.getMensaje());
                    } else {
                        new Mensaje().showModal(AlertType.INFORMATION, "Eliminar Rol", getStage(),
                                "Rol eliminado correctamente.");
                        chargeSistems();
                        newRol();
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE, "Error eliminando Rol",
                        e);
                new Mensaje().showModal(AlertType.ERROR, "Eliminar Rol", getStage(),
                        "Error eliminando el Rol.");
            }

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

    @FXML
    void onSelectiontptRoles(Event event) {

        if (tptRoles.isSelected()) {
            if (this.systems.getId() == null) {
                new Mensaje().showModal(AlertType.INFORMATION, "Roles", getStage(),
                        "Debe seleccionar un sistema.");
                tpbSistemas.getSelectionModel().select(tptSistemas);
            }
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
            // Limpiar la tabla y agregar los sistemas
            tbvSistemas.getItems().clear();
            List<SistemasDto> sistemas = (List<SistemasDto>) res.getResultado("Sistemas");
            tbvSistemas.getItems().addAll(sistemas);
        }
    }

    private void populateTextFieldsFromSystem() {
        SistemasDto selected = tbvSistemas.getSelectionModel().getSelectedItem();
        if (selected != null) {
            unbindSystems();
            this.systems = selected;
            bindSystems(false);
        }
    }

    private void populateTextFieldsFromRole() {
        RolesDto selected = tbvRoles.getSelectionModel().getSelectedItem();
        if (selected != null) {
            unbindRoles();
            this.rol = selected;
            bindRoles(false);
        }
    }

    private void chargeSistem(Long id) {
        try {
            SistemasService service = new SistemasService();
            Respuesta res = service.getSystem(id);

            if (!res.getEstado()) {
                new Mensaje().showModal(AlertType.ERROR, "Cargar Sistema", getStage(), res.getMensaje());
            } else {
                unbindSystems();
                this.systems = (SistemasDto) res.getResultado("Sistema");
                bindSystems(false);

                // Limpiar y cargar los roles del sistema actual
                ObservableList<RolesDto> rolesDelSistema = FXCollections.observableArrayList(systems.getRolesDto());
                tbvRoles.setItems(rolesDelSistema);

                System.out.println("Roles del sistema: " + rolesDelSistema.toString());
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

        tbvSistemas.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends SistemasDto> observable, SistemasDto oldValue, SistemasDto newValue) -> {
                    if (newValue != null) {
                        this.systems = newValue;
                        populateTextFieldsFromSystem();
                        chargeSistem(Long.valueOf(txfID.getText())); // Llamamos a un método específico para sistemas

                        // Usamos Platform.runLater para deseleccionar de manera segura
                        Platform.runLater(() -> tbvSistemas.getSelectionModel().clearSelection());
                    }
                });

        tbvRoles.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends RolesDto> observable, RolesDto oldValue, RolesDto newValue) -> {
                    if (newValue != null) {
                        this.rol = newValue;
                        populateTextFieldsFromRole();

                        // Usamos Platform.runLater para deseleccionar de manera segura
                        Platform.runLater(() -> tbvRoles.getSelectionModel().clearSelection());
                    }
                });

    }

    @Override
    public void initialize() {
        chargeSistems();

    }

}
