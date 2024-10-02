package cr.ac.una.chatsapp.controller;

import java.awt.image.BufferedImage;
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
import cr.ac.una.chatsapp.model.UsuariosDTO;
import cr.ac.una.chatsapp.service.UsuariosService;
import cr.ac.una.chatsapp.util.AppContext;
import cr.ac.una.chatsapp.util.FlowController;
import cr.ac.una.chatsapp.util.Formato;
import cr.ac.una.chatsapp.util.Mensaje;
import cr.ac.una.chatsapp.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RegisterController extends Controller implements Initializable {

    @FXML
    private MFXButton btnGoBack;

    @FXML
    private MFXButton btnRegister;

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

    UsuariosDTO usuariosDto;

    List<Node> requeridos = new ArrayList<>();

    @Override
    public void initialize() {
        cmbLan.getItems().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txfCed.delegateSetTextFormatter(Formato.getInstance().cedulaFormat(15));
        txfTel.delegateSetTextFormatter(Formato.getInstance().integerFormatWithMaxLength(30));
        txfCel.delegateSetTextFormatter(Formato.getInstance().integerFormatWithMaxLength(30));
        txfMail.delegateSetTextFormatter(Formato.getInstance().maxLengthFormat(100));
        txfNombre.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfLasts.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfUser.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));
        txfPassword.delegateSetTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txfStatus.delegateSetTextFormatter(Formato.getInstance().letrasFormat(100));

        this.usuariosDto = new UsuariosDTO();
        newUser();
        indicateRequiredFields();
    }

    @FXML
    void onActionBtnGoback(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("LoginView");
        ((Stage) root.getScene().getWindow()).close();

    }

    @FXML
    void onActionBtnRegister(ActionEvent event) {

        try {
            String validation = validarRequeridos();
            if (!validation.isEmpty()) {
                new Mensaje().showModal(AlertType.WARNING, "Guardar Usuario", getStage(), validation);
            } else {
                this.usuariosDto.setUsuEstado("I"); // Set as inactive or similar
                UsuariosService service = new UsuariosService();
                Respuesta respuesta = service.saveUser(usuariosDto.registers());

                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(AlertType.INFORMATION, "Guardar Usuario", getStage(),
                            respuesta.getMensaje());
                } else {
                    unbindUser();
                    this.usuariosDto = (UsuariosDTO) respuesta.getResultado("Usuario");
                    bindUser(false);
                    new Mensaje().showModal(AlertType.INFORMATION, "Guardar Usuario", getStage(),
                            "Usuario guardado correctamente, revise su correo para activacion.");

                    File file = new File("photo.png");
                    if (file.exists()) {
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Imagen borrada correctamente.");
                        } else {
                            System.out.println("No se pudo borrar la imagen.");
                        }
                    }

                    AppContext.getInstance().set("Taken", false);
                    newUser();
                    FlowController.getInstance().goViewInWindow("LoginView");
                    ((Stage) root.getScene().getWindow()).close();
                }
            }
        } catch (

        Exception e) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, "Error guardando el usuario", e);
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
                    usuariosDto.setUsuFoto(toByteArray(bufferedImage)); // Método de conversión a byte[]
                } catch (IOException e) {
                    e.printStackTrace();
                    new Mensaje().showModal(AlertType.ERROR, "Error", getStage(), "Error al cargar la imagen.");
                }
            } else {
                new Mensaje().showModal(AlertType.WARNING, "Error", getStage(), "El archivo de la foto no existe.");
            }
        }
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
        txfNombre.textProperty().bindBidirectional(usuariosDto.usuNombre);
        txfLasts.textProperty().bindBidirectional(usuariosDto.usuApellidos);
        txfCed.textProperty().bindBidirectional(usuariosDto.usuCedula);
        txfMail.textProperty().bindBidirectional(usuariosDto.usuCorreo);
        txfTel.textProperty().bindBidirectional(usuariosDto.usuTelefono);
        txfCel.textProperty().bindBidirectional(usuariosDto.usuCelular);
        cmbLan.valueProperty().bindBidirectional(usuariosDto.usuIdioma);
        txfUser.textProperty().bindBidirectional(usuariosDto.usuUsuario);
        txfPassword.textProperty().bindBidirectional(usuariosDto.usuClave);
        txfStatus.textProperty().bindBidirectional(usuariosDto.usuStatus);
        if (usuariosDto.getUsuFoto() != null) {
            imgViewUser.setImage(byteToImage(usuariosDto.getUsuFoto()));
        }
    }

    public void unbindUser() {
        // Unbind text fields
        txfNombre.textProperty().unbindBidirectional(usuariosDto.usuNombre);
        txfLasts.textProperty().unbindBidirectional(usuariosDto.usuApellidos);
        txfCed.textProperty().unbindBidirectional(usuariosDto.usuApellidos);
        txfMail.textProperty().unbindBidirectional(usuariosDto.usuCorreo);
        txfTel.textProperty().unbindBidirectional(usuariosDto.usuTelefono);
        txfCel.textProperty().unbindBidirectional(usuariosDto.usuCelular);
        cmbLan.valueProperty().unbindBidirectional(usuariosDto.usuIdioma);
        txfUser.textProperty().unbindBidirectional(usuariosDto.usuUsuario);
        txfPassword.textProperty().unbindBidirectional(usuariosDto.usuClave);
        txfStatus.textProperty().unbindBidirectional(usuariosDto.usuStatus);
        // imgViewUser.setImage(new Image("../resources/add-photo.jpg"));
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
        this.usuariosDto = new UsuariosDTO();
        bindUser(true); // Now bind with a new user object
        // Reset fields
        txfNombre.clear();
        txfNombre.requestFocus();
        cmbLan.clear();
        cmbLan.getSelectionModel().clearSelection();
        cmbLan.getItems().addAll("Español", "Inglés");

    }

    private void indicateRequiredFields() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfNombre, txfLasts, txfCed, txfMail, txfTel, txfCel, txfUser, txfPassword,
                cmbLan, imgViewUser));
    }

    private byte[] toByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }

}
