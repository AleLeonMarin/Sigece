package cr.ac.una.chatsapp.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.*;

import cr.ac.una.chatsapp.model.ChatsDTO;
import cr.ac.una.chatsapp.model.MensajesDTO;
import cr.ac.una.chatsapp.model.UsuariosDTO;
import cr.ac.una.chatsapp.service.ChatsService;
import cr.ac.una.chatsapp.service.MensajesService;
import cr.ac.una.chatsapp.util.Mensaje;
import cr.ac.una.chatsapp.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.util.Callback;
import javafx.util.Duration;

public class ChatsAppController extends Controller implements Initializable {

    @FXML
    private TableView<UsuariosDTO> tbvContactos;
    @FXML
    private TableColumn<UsuariosDTO, String> tbcContactos;
    @FXML
    private VBox vboxChats;

    private ChatsService chatsService = new ChatsService();

    private Long idEmisor;

    @FXML
    private MFXTextField txtMensaje;

    private ChatsDTO currentChat;

    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private MFXButton btnSend;

    @Override
    public void initialize() {
        idEmisor = obtenerIdEmisorActual();
        cargarUsuarios();
        tbvContactos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarChatConUsuario(newValue.getUsuId());
            }
        });

        // Inicializar el Timeline para la actualización periódica
        iniciarActualizacionPeriodica();
    }
    private Long obtenerIdEmisorActual() {
        return 2L;
    }

    private void cargarUsuarios() {
        Respuesta respuesta = chatsService.getUsuarios();
        if (respuesta.getEstado()) {
            List<UsuariosDTO> usuarios = (List<UsuariosDTO>) respuesta.getResultado("Usuarios");
            List<UsuariosDTO> usuariosActivos = new ArrayList<>();
            for (UsuariosDTO usuario : usuarios) {
                if ("A".equals(usuario.getUsuEstado()) || "I".equals(usuario.getUsuEstado())) {
                    usuariosActivos.add(usuario);
                }
            }
            ObservableList<UsuariosDTO> usuariosList = FXCollections.observableArrayList(usuariosActivos);
            tbvContactos.setItems(usuariosList);
            tbcContactos.setCellFactory(new Callback<TableColumn<UsuariosDTO, String>, TableCell<UsuariosDTO, String>>() {
                @Override
                public TableCell<UsuariosDTO, String> call(TableColumn<UsuariosDTO, String> param) {
                    return new TableCell<UsuariosDTO, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!empty) {
                                UsuariosDTO usuario = getTableRow().getItem();
                                HBox hbox = new HBox(10);
                                ImageView imageView = new ImageView();
                                if (usuario.getUsuFotoBase64() != null && !usuario.getUsuFotoBase64().isEmpty()) {
                                    try {
                                        byte[] imageBytes = usuario.getUsuFoto();
                                        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                                        imageView.setImage(new Image(bis));
                                    } catch (IllegalArgumentException e) {
                                        imageView.setImage(new Image("src/main/resources/cr/ac/una/chatsapp/resources/add-user.png"));
                                    }
                                } else {
                                    imageView.setImage(new Image("src/main/resources/cr/ac/una/chatsapp/resources/add-user.png"));
                                }
                                imageView.setFitHeight(40);
                                imageView.setFitWidth(40);
                                Label nombreLabel = new Label(usuario.getUsuNombre() + " " + usuario.getUsuApellidos());
                                Label estadoLabel = new Label(usuario.getUsuStatus());
                                estadoLabel.setStyle("-fx-text-fill: #00bfff;");
                                hbox.getChildren().addAll(imageView, nombreLabel, estadoLabel);
                                setGraphic(hbox);
                            }
                        }
                    };
                }
            });
        } else {
            System.out.println("Error obteniendo los usuarios: " + respuesta.getMensaje());
        }
    }

    private void cargarChatConUsuario(Long idReceptor) {
        vboxChats.getChildren().clear();
        Respuesta respuesta = chatsService.getChatsEntreUsuarios(idEmisor, idReceptor);

        if (respuesta.getEstado()) {
            List<ChatsDTO> chats = (List<ChatsDTO>) respuesta.getResultado("Chats");
            if (chats != null && !chats.isEmpty()) {
                currentChat = chats.get(0);
                mostrarMensajesDelChat(currentChat.getMensajesList());
            } else {
                System.out.println("No hay chats en este chat.");
            }
        } else {
            System.out.println("Error obteniendo los chats: " + respuesta.getMensaje());
        }
    }

    private void mostrarMensajesDelChat(List<MensajesDTO> mensajes) {
        vboxChats.getChildren().clear();
        if (mensajes != null && !mensajes.isEmpty()) {
            mensajes.sort(Comparator.comparing(MensajesDTO::getSmsTiempo));
            for (MensajesDTO mensaje : mensajes) {
                HBox hbox = new HBox();
                Label mensajeLabel = new Label(mensaje.getSmsTexto());
                hbox.setPrefWidth(vboxChats.getPrefWidth() - 20);
                hbox.setMaxWidth(vboxChats.getPrefWidth() - 20);
                mensajeLabel.setWrapText(true);
                mensajeLabel.setMaxWidth(hbox.getPrefWidth() * 0.75);

                Long emisorIdMensaje = mensaje.getEmisorId().getUsuId();
                if (emisorIdMensaje != null && emisorIdMensaje.equals(idEmisor)) {
                    hbox.setAlignment(Pos.CENTER_RIGHT);
                    mensajeLabel.setStyle("-fx-background-color: #2390b8; -fx-padding: 10px; -fx-background-radius: 10px;");
                } else {
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    mensajeLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-background-radius: 10px;");
                }

                hbox.getChildren().add(mensajeLabel);
                vboxChats.getChildren().add(hbox);
            }
        }
    }

    // Iniciar el timeline para actualización periódica
    private void iniciarActualizacionPeriodica() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> actualizarMensajes()));
        timeline.setCycleCount(Timeline.INDEFINITE);  // Se repite indefinidamente
        timeline.play();  // Iniciar la actualización automática
    }

    private void actualizarMensajes() {
        if (currentChat != null) {
            Respuesta respuesta = chatsService.getChatsEntreUsuarios(idEmisor, currentChat.getReceptorId().getUsuId());
            if (respuesta.getEstado()) {
                List<ChatsDTO> chats = (List<ChatsDTO>) respuesta.getResultado("Chats");
                if (chats != null && !chats.isEmpty()) {
                    ChatsDTO nuevoChat = chats.get(0);  // Obtener la nueva lista de mensajes
                    List<MensajesDTO> nuevosMensajes = nuevoChat.getMensajesList();
                    if (!nuevosMensajes.equals(currentChat.getMensajesList())) {  // Solo actualizar si hay nuevos mensajes
                        currentChat = nuevoChat;
                        mostrarMensajesDelChat(nuevosMensajes);  // Actualizar la interfaz
                    }
                }
            }
        }
    }

    @FXML
    void onActonBtnSend(ActionEvent event) {
        String textoMensaje = txtMensaje.getText();
        if (textoMensaje.isEmpty()) {
            Mensaje mensaje = new Mensaje();
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "El campo de mensaje no puede estar vacío.");
            return;
        }
        Long idReceptor = tbvContactos.getSelectionModel().getSelectedItem().getUsuId();
        if (currentChat == null) {
            System.out.println("Error: No hay chat cargado.");
            return;
        }

        MensajesDTO mensajeDto = new MensajesDTO();
        mensajeDto.setSmsTexto(textoMensaje);
        UsuariosDTO receptor = new UsuariosDTO();
        UsuariosDTO emisor = new UsuariosDTO();
        receptor.setUsuId(idReceptor);
        emisor.setUsuId(obtenerIdEmisorActual());
        mensajeDto.setEmisorId(emisor);
        mensajeDto.setChatId(currentChat);

        MensajesService mensajesService = new MensajesService();
        Respuesta respuesta = mensajesService.guardarMensaje(mensajeDto);

        if (respuesta.getEstado()) {
            System.out.println("Mensaje enviado correctamente.");
            HBox hbox = new HBox();
            Label mensajeLabel = new Label(textoMensaje);
            hbox.setPrefWidth(vboxChats.getPrefWidth() - 20);
            hbox.setMaxWidth(vboxChats.getPrefWidth() - 20);
            mensajeLabel.setWrapText(true);
            mensajeLabel.setMaxWidth(hbox.getPrefWidth() * 0.75);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            mensajeLabel.setStyle("-fx-background-color: #2390b8; -fx-padding: 10px; -fx-background-radius: 10px;");
            hbox.getChildren().add(mensajeLabel);
            vboxChats.getChildren().add(hbox);
            txtMensaje.clear();
        } else {
            System.out.println("Error enviando el mensaje: " + respuesta.getMensaje());
        }
    }
}
