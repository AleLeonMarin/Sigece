package cr.ac.una.tarea.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import cr.ac.una.tarea.model.RolesDto;
import cr.ac.una.tarea.model.SistemasDto;
import cr.ac.una.tarea.model.UsuariosDto;
import cr.ac.una.tarea.service.SistemasService;
import cr.ac.una.tarea.service.UsuariosService;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Formato;
import cr.ac.una.tarea.util.Mensaje;
import cr.ac.una.tarea.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import java.awt.image.BufferedImage;;

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
    private MFXComboBox<RolesDto> cmbRoles;

    @FXML
    private ImageView imgViewUser;

    @FXML
    private StackPane root;

    @FXML
    private TableColumn<RolesDto, String> tbcIdRol;

    @FXML
    private TableColumn<RolesDto, String> tbcRolNombre;

    @FXML
    private TableColumn<RolesDto, String> tbcSistemaNombre;

    @FXML
    private TableView<RolesDto> tbvRoles;

    @FXML
    private TableView<SistemasDto> tbvUsers;

    @FXML
    private Tab tptMantenimiento;

    @FXML
    private Tab tptRoles;

    @FXML
    private TabPane tbpUsuarios;

    @FXML
    private MFXTextField txfCed;

    @FXML
    private MFXTextField txfCel;

    @FXML
    private MFXTextField txfID;

    @FXML
    private MFXTextField txfIdSistema;

    @FXML
    private MFXTextField txfLasts;

    @FXML
    private MFXTextField txfMail;

    @FXML
    private MFXTextField txfNombre;

    @FXML
    private MFXTextField txfNombreSistema;

    @FXML
    private MFXTextField txfPassword;

    @FXML
    private MFXTextField txfStatus;

    @FXML
    private MFXTextField txfTel;

    @FXML
    private MFXTextField txfUser;

    @FXML
    TableColumn<SistemasDto, RolesDto> tbcRol;

    UsuariosDto usuariosDto;

    SistemasDto systems;

    RolesDto rol;

    File file;

    List<Node> requeridos = new ArrayList<>();

    ObservableList<SistemasDto> sistemasList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txfID.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txfCed.delegateSetTextFormatter(Formato.getInstance().cedulaFormat(15));
        txfTel.delegateSetTextFormatter(Formato.getInstance().integerFormatWithMaxLength(30));
        txfCel.delegateSetTextFormatter(Formato.getInstance().integerFormatWithMaxLength(30));
        txfMail.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfNombre.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfLasts.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfUser.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfPassword.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfStatus.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));

        // Initialize TableView columns rols
        tbcIdRol.setCellValueFactory(cd -> cd.getValue().id);
        tbcRolNombre.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getNombre()));
        TableColumn<RolesDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellValueFactory(
                (TableColumn.CellDataFeatures<RolesDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<RolesDto, Boolean> p) -> new ButtonCell());
        tbvRoles.getColumns().add(tbcEliminar);

        this.usuariosDto = new UsuariosDto();
        this.systems = new SistemasDto();
        this.rol = new RolesDto();
        newUser(); // Resets to a new user
        indicateRequiredFields(); // Marks required fields

        // Add listener for TableView row selection
        tbvUsers.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends SistemasDto> observable, SistemasDto oldvalue, SistemasDto newValue) -> {
                    unbindSystems();
                    if (newValue != null) {
                        this.systems = newValue;
                        bindSystems(false);
                    }
                }

        );
        ;
    }

    private Image byteToImage(byte[] bytes) {
        try {
            if (bytes != null && bytes.length > 0) {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                return new Image(bis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        if (usuariosDto.getFoto() != null) {
            imgViewUser.setImage(byteToImage(usuariosDto.getFoto()));
        }
    }

    public void unbindUser() {
        // Unbind text fields
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
        // imgViewUser.setImage(new Image("../resources/add-photo.jpg"));
    }

    private void bindSystems(boolean newSystem) {
        if (!newSystem) {
            txfIdSistema.textProperty().bind(this.systems.id);
        }
        txfNombreSistema.textProperty().bindBidirectional(this.systems.nombre);
        cmbRoles.setConverter(new StringConverter<RolesDto>() {
            @Override
            public String toString(RolesDto rolesDto) {
                return rolesDto != null ? rolesDto.getNombre() : "";
            }

            @Override
            public RolesDto fromString(String string) {
                return null;
            }
        });

        if (this.systems.getRolesDto() != null) {
            cmbRoles.setItems(FXCollections.observableArrayList(this.systems.getRolesDto()));
        }
    }

    private void unbindSystems() {
        txfIdSistema.textProperty().unbind();
        txfNombreSistema.textProperty().unbindBidirectional(this.systems.nombre);
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

    private void newUser() {
        unbindUser(); // Unbind after initializing the DTO
        this.usuariosDto = new UsuariosDto();
        bindUser(true); // Now bind with a new user object
        // Reset fields
        txfID.clear();
        txfID.requestFocus();
        cmbLan.clear();
        cmbLan.getSelectionModel().clearSelection();
        cmbLan.getItems().addAll("Español", "Inglés");

    }

    private void newSystem() {
        unbindSystems();
        tbvUsers.getSelectionModel().select(null);
        this.systems = new SistemasDto();
        bindSystems(true);
        txfIdSistema.clear();
        cmbRoles.getItems().clear();
        txfIdSistema.requestFocus();
    }

    private void indicateRequiredFields() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfNombre, txfLasts, txfCed, txfMail, txfTel, txfCel, cmbLan, txfUser,
                txfPassword, txfStatus));
    }

    /* Method to charge generic image where the user photo will be shown */
    private void chargeGenericImage(ImageView photo, File file) {
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo.setImage(image);
            } catch (IOException e) {
                new Mensaje().showModal(AlertType.ERROR, "Error", getStage(), "Error al cargar la imagen.");
            }
        }

    }

    public void chargeRoles() {

        tbvRoles.getItems().clear();
        tbvRoles.setItems(FXCollections.observableArrayList(this.usuariosDto.getRolesDto()));
        List<SistemasDto> sistemas = (List<SistemasDto>) rol.getSistema();
        if (sistemas != null && !sistemas.isEmpty()) {
            tbcSistemaNombre.setCellValueFactory(cd -> cd.getValue().nombre);
        }
        tbvRoles.refresh();
    }

    private void chargeUser(Long id) {

        try {
            UsuariosService service = new UsuariosService();
            Respuesta respuesta = service.getUser(id);
            if (respuesta.getEstado()) {
                unbindUser();
                this.usuariosDto = (UsuariosDto) respuesta.getResultado("Usuario");
                bindUser(false);
                cmbLan.getSelectionModel().selectItem(this.usuariosDto.getIdioma());
                validarRequeridos();
                chargeRoles();
            } else {
                new Mensaje().showModal(AlertType.INFORMATION, "Buscar Usuario", getStage(), respuesta.getMensaje());
            }
        } catch (Exception e) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, "Error buscando el usuario ", e);
            new Mensaje().showModal(AlertType.ERROR, "Buscar Usuario", getStage(), "Error buscando el usuario.");
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
            }
        } catch (Exception e) {
            Logger.getLogger(AdminSystemController.class.getName()).log(Level.SEVERE, "Error cargando sistema", e);
            new Mensaje().showModal(AlertType.ERROR, "Cargar Sistema", getStage(), "Error cargando el Sistema.");
        }
    }

    private void columnsTable() {
        // Configuración de la columna para el ID
        TableColumn<SistemasDto, String> tbcIdRol = new TableColumn<>("ID");
        tbcIdRol.setCellValueFactory(cd -> cd.getValue().id);
        tbvUsers.getColumns().add(tbcIdRol);

        // Configuración de la columna para el nombre
        TableColumn<SistemasDto, String> tbcRolNombre = new TableColumn<>("Nombre");
        tbcRolNombre.setCellValueFactory(cd -> cd.getValue().nombre);
        tbvUsers.getColumns().add(tbcRolNombre);

        // Configuración de la columna para el rol
        tbcRol = new TableColumn<>("Rol");
        tbcRol.setCellValueFactory(cd -> {
            RolesDto selectedRole = cmbRoles.getSelectionModel().getSelectedItem();
            return new SimpleObjectProperty<>(selectedRole);
        });

        tbcRol.setCellFactory(column -> new TableCell<SistemasDto, RolesDto>() {
            @Override
            protected void updateItem(RolesDto rol, boolean empty) {
                super.updateItem(rol, empty);
                if (empty || rol == null) {
                    setText(null);
                } else {
                    // Muestra el nombre del rol
                    setText(rol.getNombre());
                }
            }
        });

        tbvUsers.getColumns().add(tbcRol);
    }

    @Override
    public void initialize() {
    }

    @FXML
    void onKeyPressedTxId(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txfID.getText().isBlank()) {
            chargeUser(Long.valueOf(txfID.getText()));
        }

    }

    @FXML
    void onKeyPressedTxfIdSistema(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER && !txfIdSistema.getText().isBlank()) {
            chargeSistem(Long.valueOf(txfIdSistema.getText()));
        }

    }

    @FXML
    void selectionChangeTabUsuarios(Event event) {

        if (tptRoles.isSelected()) {
            if (this.usuariosDto.getId() == null) {
                new Mensaje().showModal(AlertType.INFORMATION, "Roles de Usuario", getStage(),
                        "Debe seleccionar un usuario.");
                tbpUsuarios.getSelectionModel().select(tptMantenimiento);
            } else {
                new Mensaje().showModal(AlertType.INFORMATION, "Inclusion", getStage(), "Solo puede agregar un rol de un sistema a la vez.");
                tbvUsers.getItems().clear();
                tbvUsers.getColumns().clear();
                if (tbvUsers.getColumns().isEmpty()) {
                    columnsTable();
                }
                newSystem();
            }
        }
    }

    @FXML
    void onActionBtnAddUser(ActionEvent event) {

        if (this.systems.getId() == null || this.systems.getNombre().isEmpty() || cmbRoles.getSelectedItem() == null) {
            new Mensaje().showModal(AlertType.ERROR, "Agregar Rol", getStage(),
                    "Es necesario cargar un sistema y seleccionar un rol.");
        } else if (tbvUsers.getItems() == null || !tbvUsers.getItems().stream().anyMatch(s -> s.equals(this.systems))) {
            this.usuariosDto.setModificado(true);
            tbvUsers.getItems().add(this.systems);
            tbvUsers.refresh();
        }

        newSystem();
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {

        try {
            if (this.usuariosDto.getId() == null) {
                new Mensaje().showModal(AlertType.INFORMATION, "Eliminar Usuario", getStage(),
                        "No se ha seleccionado un usuario.");
            } else {
                UsuariosService service = new UsuariosService();
                Respuesta respuesta = service.deleteUser(this.usuariosDto.getId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(AlertType.INFORMATION, "Eliminar Usuario", getStage(),
                            respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(AlertType.INFORMATION, "Eliminar Usuario", getStage(),
                            "Usuario eliminado correctamente.");
                    newUser();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, "Error eliminando el usuario ", e);
            new Mensaje().showModal(AlertType.ERROR, "Eliminar Usuario", getStage(), "Error eliminando el usuario.");
        }

    }

    @FXML
    void onActionBtnNew(ActionEvent event) {

        if (tptRoles.isSelected()) {
            newSystem();
        } else if (tptMantenimiento.isSelected()) {
            if (new Mensaje().showConfirmation("Limpiar Usuario", getStage(),
                    "¿Esta seguro que desea limpiar el registro?")) {
                newUser();
                tbvRoles.getItems().clear();
            }
        }

    }

    @FXML
    void onActionBtnSave(ActionEvent event) {
        try {
            String validation = validarRequeridos();
            if (!validation.isEmpty()) {
                new Mensaje().showModal(AlertType.WARNING, "Guardar Usuario", getStage(), validation);
            } else {

                if (this.usuariosDto.getId() == null) {
                    this.usuariosDto.setEstado("I");
                }

                List<RolesDto> rolesList = new ArrayList<>();
                for (SistemasDto sistema : tbvUsers.getItems()) {
                    RolesDto selectedRole = (RolesDto) tbcRol.getCellData(sistema); // Get the RolesDto from the cell
                    if (selectedRole != null) {
                        selectedRole.setModificado(true);
                        rolesList.add(selectedRole);
                    }
                }

                // Assign the roles to the user
                this.usuariosDto.setRolesDto(rolesList);

                rolesList.forEach(rol -> System.out.println("Rol seleccionado: " + rol.getNombre()));

                // Guardar el usuario y sus roles seleccionados
                UsuariosService service = new UsuariosService();
                Respuesta respuesta = service.saveUser(usuariosDto.registers());

                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(AlertType.INFORMATION, "Guardar Usuario", getStage(),
                            respuesta.getMensaje());
                } else {
                    unbindUser();
                    this.usuariosDto = (UsuariosDto) respuesta.getResultado("Usuario");
                    bindUser(false);
                    new Mensaje().showModal(AlertType.INFORMATION, "Guardar Usuario", getStage(),
                            "Usuario guardado correctamente.");

                    File file = new File("photo.png");
                    if (file.exists()) {
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Imagen borrada correctamente.");
                        } else {
                            System.out.println("No se pudo borrar la imagen.");
                        }
                    }
                }

                AppContext.getInstance().set("Taken", false);

            }
        } catch (Exception e) {
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, "Error guardando el usuario", e);
            new Mensaje().showModal(AlertType.ERROR, "Guardar Usuario", getStage(), "Error guardando el usuario.");
        }
    }

    @FXML
    void onActionImgvPhoto(MouseEvent event) {

        FlowController.getInstance().goViewInWindowModal("CamView", getStage(), false);

    }

    @FXML
    void onActionRoot(MouseEvent event) {
        Boolean photoTaken = (Boolean) AppContext.getInstance().get("Taken");

        if (photoTaken != null && photoTaken) {
            File file = new File("photo.png");
            if (file.exists()) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    imgViewUser.setImage(image);
                    usuariosDto.setFoto(toByteArray(bufferedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                    new Mensaje().showModal(AlertType.ERROR, "Error", getStage(), "Error al cargar la imagen.");
                }
            } else {
                new Mensaje().showModal(AlertType.WARNING, "Error", getStage(), "El archivo de la foto no existe.");
            }
        }
    }

    private byte[] toByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }

    private class ButtonCell extends TableCell<RolesDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-tbvUser-btnDelete");

            cellButton.setOnAction((ActionEvent t) -> {
                RolesDto rol = (RolesDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                usuariosDto.getRolesDtoEliminados().add(rol);
                tbvRoles.getItems().remove(rol);
                tbvRoles.refresh();
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

}
