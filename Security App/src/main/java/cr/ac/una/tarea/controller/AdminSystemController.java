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

import cr.ac.una.tarea.model.RolesDto;
import cr.ac.una.tarea.model.SistemasDto;
import cr.ac.una.tarea.model.UsuariosDto;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private MFXButton btnAdd;

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
    private TableView<UsuariosDto> tbvUsers;

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

    RolesDto rol;

    UsuariosDto users;

    SistemasDto systems;

    List<Node> requeridos = new ArrayList<>();

    @FXML
    void onActionBtnAccept(ActionEvent event) {

    }

    @FXML
    void onActionBtnAddUser(ActionEvent event) {

    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {

    }

    @FXML
    void onActionBtnNew(ActionEvent event) {

    }

    private void createColumnsUsers() {

        TableColumn<UsuariosDto, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(cd -> cd.getValue().id);
        tbvUsers.getColumns().add(colID);

        TableColumn<UsuariosDto, String> colName = new TableColumn<>("Nombre");
        colName.setCellValueFactory(cd -> cd.getValue().nombre);
        tbvUsers.getColumns().add(colName);

        TableColumn<UsuariosDto, Boolean> colRol = new TableColumn<>("Rol");
        colRol.setCellValueFactory((TableColumn.CellDataFeatures<UsuariosDto, Boolean> p) -> new SimpleBooleanProperty(
                p.getValue() != null));

        colRol.setCellFactory((TableColumn<UsuariosDto, Boolean> p) -> new ComboBoxCell());
        tbvUsers.getColumns().add(colRol);

        TableColumn<UsuariosDto, Boolean> colDelete = new TableColumn<>("Eliminar");
        colDelete.setCellValueFactory(
                (TableColumn.CellDataFeatures<UsuariosDto, Boolean> p) -> new SimpleBooleanProperty(
                        p.getValue() != null));
        colDelete.setCellFactory((TableColumn<UsuariosDto, Boolean> p) -> new ButtonCell());
        tbvUsers.getColumns().add(colDelete);

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
        requeridos.addAll(Arrays.asList(txfID, txfName, txfIdRol, txfNombreRol));
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

    private void newUser() {

        this.users = new UsuariosDto();
        unbindUsers();
        bindUsers(true);
        txfIdUser.clear();
        txfIdUser.requestFocus();

    }

    // Bind methods

    private void bindSystems(Boolean isNew) {

        if (!isNew) {
            txfID.textProperty().bind(this.systems.id);
        }
        txfName.textProperty().bindBidirectional(this.systems.nombre);

    }

    private void bindRoles(Boolean isNew) {

        if (!isNew) {
            txfIdRol.textProperty().bind(this.rol.id);
        }
        txfNombreRol.textProperty().bindBidirectional(this.rol.nombre);

    }

    private void bindUsers(Boolean isNew) {

        if (!isNew) {
            txfIdUser.textProperty().bind(this.users.id);
        }
        txfNombreUser.textProperty().bindBidirectional(this.users.nombre);

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

    private void unbindUsers() {

        txfIdUser.textProperty().unbind();
        txfNombreUser.textProperty().unbindBidirectional(this.users.nombre);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Columns creation
        createColumnsSystems();
        createColumnsRoles();
        createColumnsUsers();

        // Ititialize the dto's

        this.rol = new RolesDto();
        this.users = new UsuariosDto();
        this.systems = new SistemasDto();

        // New methods

        newSystem();
        newRol();
        newUser();

        // Requiere fields

        indicateRequiredFields();


    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub

    }

    private class ButtonCell extends TableCell<UsuariosDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {

            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-tbvUser-btnDelete");

            cellButton.setOnAction((ActionEvent t) -> {
                UsuariosDto user = (UsuariosDto) ButtonCell.this.getTableView().getItems()
                        .get(ButtonCell.this.getIndex());
                rol.getUsuariosDto().remove(user);
                tbvUsers.getItems().remove(user);
                tbvUsers.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    private class ComboBoxCell extends TableCell<UsuariosDto, Boolean> {

        final ComboBox<String> cellComboBox = new ComboBox<>();

        ComboBoxCell() {
            // Aquí puedes agregar los elementos que quieres que el ComboBox contenga
            cellComboBox.getItems().addAll("Option 1", "Option 2", "Option 3");

            cellComboBox.setOnAction((ActionEvent event) -> {
                UsuariosDto user = (UsuariosDto) ComboBoxCell.this.getTableView().getItems()
                        .get(ComboBoxCell.this.getIndex());
                String selectedOption = cellComboBox.getValue();

                // Aquí puedes definir qué hacer cuando se selecciona una opción
                if ("Option 1".equals(selectedOption)) {
                    // Acción para la opción 1
                    rol.getUsuariosDto().remove(user);
                    tbvUsers.getItems().remove(user);
                } else if ("Option 2".equals(selectedOption)) {
                    // Acción para la opción 2
                    // Otro comportamiento
                }

                tbvUsers.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellComboBox);
            } else {
                setGraphic(null);
            }
        }
    }

}
