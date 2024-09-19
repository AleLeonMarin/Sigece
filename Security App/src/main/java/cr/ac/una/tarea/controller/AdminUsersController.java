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
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import cr.ac.una.tarea.model.RolesDto;
import cr.ac.una.tarea.model.SistemasDto;
import cr.ac.una.tarea.model.UsuariosDto;
import cr.ac.una.tarea.service.SistemasService;
import cr.ac.una.tarea.service.UsuariosService;
import cr.ac.una.tarea.util.AppContext;
import cr.ac.una.tarea.util.FlowController;
import cr.ac.una.tarea.util.Mensaje;
import cr.ac.una.tarea.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
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
    private MFXComboBox<String> cmbRoles;

    @FXML
    private ImageView imgViewUser;

    @FXML
    private StackPane root;

    @FXML
    private TableColumn<RolesDto, String> tbcIdRol;

    @FXML
    private TableColumn<RolesDto, String> tbcRolNombre;

    @FXML
    private TableColumn<SistemasDto, String> tbcSistemaNombre;

    @FXML
    private TableView<RolesDto> tbvRoles;

    @FXML
    private TableView<SistemasDto> tbvUsers;

    @FXML
    private Tab tptMantenimiento;

    @FXML
    private Tab tptRoles;

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

    UsuariosDto usuariosDto;

    SistemasDto systems;

    List<Node> requeridos = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tbcIdRol.setCellValueFactory(cd -> cd.getValue().id);
        tbcRolNombre.setCellValueFactory(cd -> cd.getValue().nombre);
        this.usuariosDto = new UsuariosDto();
        this.systems = new SistemasDto();
        newUser();
        indicateRequiredFields();

        tbvUsers.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends SistemasDto> observable, SistemasDto oldValue,
                        SistemasDto newValue) -> {
                    if (newValue != null) {
                        this.systems = newValue;
                        bindSystems(false);
                    }
                });

    }

    private Image byteToImage(byte[] bytes) {
        try {
            if (bytes != null && bytes.length > 0) {
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                return new Image(bis);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Puedes cambiarlo a un logger para mejor manejo
        }
        return null; // Si la imagen no es válida o los bytes están vacíos, retornamos null
    }

    private void bindUser(boolean newUser) {
        if (!newUser) {
            txfID.textProperty().bind(this.usuariosDto.id);
        }
        txfNombre.textProperty().bindBidirectional(this.usuariosDto.nombre);
        txfLasts.textProperty().bindBidirectional(this.usuariosDto.apellidos);
        txfCed.textProperty().bindBidirectional(this.usuariosDto.cedula);
        txfMail.textProperty().bindBidirectional(this.usuariosDto.correo);
        txfTel.textProperty().bindBidirectional(this.usuariosDto.telefono);
        txfCel.textProperty().bindBidirectional(this.usuariosDto.celular);
        cmbLan.valueProperty().bindBidirectional(this.usuariosDto.idioma);
        txfUser.textProperty().bindBidirectional(usuariosDto.usuario);
        txfPassword.textProperty().bindBidirectional(this.usuariosDto.clave);
        txfStatus.textProperty().bindBidirectional(this.usuariosDto.status);
        if (this.usuariosDto.getFoto() != null) {
            imgViewUser.setImage(byteToImage(this.usuariosDto.getFoto()));
        }
    }

    public void unbindUser() {
        // Unbind text fields
        txfID.textProperty().unbind();
        txfNombre.textProperty().unbindBidirectional(this.usuariosDto.nombre);
        txfLasts.textProperty().unbindBidirectional(this.usuariosDto.apellidos);
        txfCed.textProperty().unbindBidirectional(this.usuariosDto.cedula);
        txfMail.textProperty().unbindBidirectional(this.usuariosDto.correo);
        txfTel.textProperty().unbindBidirectional(this.usuariosDto.telefono);
        txfCel.textProperty().unbindBidirectional(this.usuariosDto.celular);
        cmbLan.valueProperty().unbindBidirectional(this.usuariosDto.idioma);
        txfUser.textProperty().unbindBidirectional(this.usuariosDto.usuario);
        txfPassword.textProperty().unbindBidirectional(this.usuariosDto.clave);
        txfStatus.textProperty().unbindBidirectional(this.usuariosDto.status);

        // Set the default image
        File file = new File("src/main/resources/cr/ac/una/tarea/resources/add-photo.jpg");
        chargeGenericImage(imgViewUser, file);
    }

    private void bindSystems(boolean newSystem) {
        if (!newSystem) {
            txfIdSistema.textProperty().bind(this.systems.id);
        }
        txfNombreSistema.textProperty().bindBidirectional(this.systems.nombre);

        // Limpiar los items anteriores si es necesario
        cmbRoles.getItems().clear();

        // Agregar los nombres de los roles al ComboBox
        for (RolesDto rol : this.systems.getRolesDto()) {
            cmbRoles.getItems().add(rol.getNombre()); // Usar add() en lugar de addAll()
        }
    }

    private void unbindSystems() {
        txfIdSistema.textProperty().unbind();
        txfNombreSistema.textProperty().unbindBidirectional(this.systems.nombre);
        cmbRoles.getItems().clear();
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
        this.usuariosDto = new UsuariosDto();
        unbindUser(); // Unbind after initializing the DTO
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
        this.systems = new SistemasDto();
        bindSystems(true);
        txfIdSistema.clear();
        txfIdSistema.requestFocus();
        cmbRoles.getItems().clear();
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
                e.printStackTrace();
            }
        }

    }

    private void chargeUser(Long id) {

        try {
            UsuariosService service = new UsuariosService();
            Respuesta respuesta = service.getUser(id);
            if (respuesta.getEstado()) {
                unbindUser();
                this.usuariosDto = (UsuariosDto) respuesta.getResultado("Usuario");
                bindUser(false);
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

    public void chargeRoles() {

        tbvRoles.getItems().clear();
        tbvRoles.setItems(this.usuariosDto.getRolesDto());
        tbvRoles.refresh();
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

        TableColumn<SistemasDto, String> tbcIdRol = new TableColumn<>("ID");
        tbcIdRol.setCellValueFactory(cd -> cd.getValue().id);
        tbvUsers.getColumns().add(tbcIdRol);

        TableColumn<SistemasDto, String> tbcRolNombre = new TableColumn<>("Nombre");
        tbcRolNombre.setCellValueFactory(cd -> cd.getValue().nombre);
        tbvUsers.getColumns().add(tbcRolNombre);

        TableColumn<SistemasDto, String> tbcRol = new TableColumn<>("Rol");
        tbcRol.setCellValueFactory(cd -> {
            SistemasDto sistema = cd.getValue();
            String selectedRoleName = cmbRoles.getSelectionModel().getSelectedItem();

            System.out.println("Selected Role: " + selectedRoleName); // Imprimir el rol seleccionado

            if (sistema.getRolesDto() != null && !sistema.getRolesDto().isEmpty()) {
                // Busca el rol en la lista de roles del sistema
                RolesDto rol = sistema.getRolesDto().stream()
                        .filter(r -> r.getNombre().equals(selectedRoleName))
                        .findFirst()
                        .orElse(null);

                if (rol != null) {
                    System.out.println("Found Role: " + rol.getNombre()); // Imprimir el rol encontrado
                    return new SimpleStringProperty(rol.getNombre());
                } else {
                    System.out.println("No matching role found for: " + selectedRoleName); // Imprimir si no se
                                                                                           // encuentra el rol
                }
            } else {
                System.out.println("RolesDto is null or empty"); // Imprimir si la lista de roles está vacía
            }
            return new SimpleStringProperty("Sin rol");
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

        if (event.getCode() == KeyCode.ENTER && !txfID.getText().isBlank()) {
            chargeSistem(Long.valueOf(txfID.getText()));
        }

    }

    @FXML
    void selectionChangeTabUsuarios(Event event) {

        if (tptRoles.isSelected()) {
            if (this.usuariosDto.getId() == null) {
                new Mensaje().showModal(AlertType.INFORMATION, "Roles de Usuario", getStage(),
                        "Debe seleccionar un usuario.");
                tptMantenimiento.getTabPane().getSelectionModel().select(tptMantenimiento);
            }

            columnsTable();
        }

    }

    @FXML
    void onActionBtnAddUser(ActionEvent event) {

        if (this.systems.getId() == null || this.systems.getNombre() == null || this.systems.getNombre().isEmpty()
                || cmbRoles.getSelectedItem() == null) {

            new Mensaje().showModal(AlertType.ERROR, "Agregar Rol", getStage(),
                    "Es necesario cargar un sistema y seleccionar un rol.");
        } else if (tbvUsers.getItems() == null
                || !tbvUsers.getItems().stream().anyMatch(s -> s.equals(this.systems))) {

            this.usuariosDto.setModificado(true);
            tbvUsers.getItems().add(this.systems);
            tbvUsers.refresh();
        }

        // Limpia o reinicia los valores del sistema (si es necesario)
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
            String validacion = validarRequeridos();
            if (!validacion.isEmpty()) {
                new Mensaje().showModal(AlertType.WARNING, "Guardar Usuario", getStage(), validacion);
            } else {
                this.usuariosDto.setEstado("I");
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
                    System.out.println(usuariosDto.toString());

                    // Borrar la imagen del archivo si existe
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
            Logger.getLogger(AdminUsersController.class.getName()).log(Level.SEVERE, "Error guardando el usuario ", e);
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
                    // Actualiza la foto en el DTO si es necesario
                    usuariosDto.setFoto(toByteArray(bufferedImage)); // Método de conversión a byte[]
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

}
