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

    @FXML
    private MFXButton BtnDeleteChat;

    private ChatsDTO currentChat;

    //pruebas
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
        currentChat = null;

        Respuesta respuesta = chatsService.getChatsEntreUsuarios(idEmisor, idReceptor);

        if (respuesta.getEstado()) {
            List<ChatsDTO> chats = (List<ChatsDTO>) respuesta.getResultado("Chats");
            if (chats != null && !chats.isEmpty()) {
                currentChat = chats.get(0);
                mostrarMensajesDelChat(currentChat.getMensajesList());
            } else {
                Label noChatLabel = new Label("No hay un chat con este usuario, se creará cuando envíes un mensaje.");
                noChatLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                vboxChats.getChildren().add(noChatLabel);
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

                // Crear botón de eliminar
                Button btnEliminar = new Button("Eliminar");
                btnEliminar.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                btnEliminar.setOnAction(event -> onActionEliminarMensaje(mensaje)); // Acción para eliminar el mensaje

                // Determinar si el mensaje fue enviado por el emisor actual
                Long emisorIdMensaje = mensaje.getEmisorId().getUsuId();
                if (emisorIdMensaje != null && emisorIdMensaje.equals(idEmisor)) {
                    hbox.setAlignment(Pos.CENTER_RIGHT);
                    mensajeLabel.setStyle("-fx-background-color: #2390b8; -fx-padding: 10px; -fx-background-radius: 10px;");
                } else {
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    mensajeLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-background-radius: 10px;");
                }

                hbox.getChildren().addAll(mensajeLabel, btnEliminar);
                vboxChats.getChildren().add(hbox);
            }
        } else {
            Label noMessagesLabel = new Label("No hay mensajes en este chat.");
            noMessagesLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
            vboxChats.getChildren().add(noMessagesLabel);
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
        if (idReceptor == null) {
            System.out.println("Error: No se ha seleccionado ningún contacto.");
            return;
        }

        // Verificar si hay un chat cargado, si no, crearlo
        if (currentChat == null) {
            System.out.println("No hay chat cargado. Creando nuevo chat...");

            // Crear un nuevo chat entre el emisor y el receptor
            ChatsDTO nuevoChat = new ChatsDTO();
            UsuariosDTO emisor = new UsuariosDTO();
            UsuariosDTO receptor = new UsuariosDTO();

            emisor.setUsuId(obtenerIdEmisorActual());
            receptor.setUsuId(idReceptor);

            nuevoChat.setEmisorId(emisor);
            nuevoChat.setReceptorId(receptor);

            // Guardar el nuevo chat en la base de datos
            Respuesta respuestaChat = chatsService.guardarChat(nuevoChat);
            if (respuestaChat.getEstado()) {
                currentChat = (ChatsDTO) respuestaChat.getResultado("Chat");
                System.out.println("Nuevo chat creado con éxito.");
            } else {
                System.out.println("Error creando el chat: " + respuestaChat.getMensaje());
                return;
            }
        }

        // Crear el mensaje para el chat actual o nuevo
        MensajesDTO mensajeDto = new MensajesDTO();
        mensajeDto.setSmsTexto(textoMensaje);

        UsuariosDTO emisor = new UsuariosDTO();
        UsuariosDTO receptor = new UsuariosDTO();
        receptor.setUsuId(idReceptor);
        emisor.setUsuId(obtenerIdEmisorActual());
        mensajeDto.setEmisorId(emisor);
        mensajeDto.setChatId(currentChat);

        // Llamar al servicio para guardar el mensaje
        MensajesService mensajesService = new MensajesService();
        Respuesta respuesta = mensajesService.guardarMensaje(mensajeDto);

        if (respuesta.getEstado()) {
            System.out.println("Mensaje enviado correctamente.");

            // Manually add the new message to the VBox
            HBox hbox = new HBox();
            Label mensajeLabel = new Label(textoMensaje);
            hbox.setPrefWidth(vboxChats.getPrefWidth() - 20);
            hbox.setMaxWidth(vboxChats.getPrefWidth() - 20);
            mensajeLabel.setWrapText(true);
            mensajeLabel.setMaxWidth(hbox.getPrefWidth() * 0.75);

            // Since this is the sender's message, align it to the right
            hbox.setAlignment(Pos.CENTER_RIGHT);
            mensajeLabel.setStyle("-fx-background-color: #2390b8; -fx-padding: 10px; -fx-background-radius: 10px;");

            hbox.getChildren().add(mensajeLabel);
            vboxChats.getChildren().add(hbox);  // Add the message directly to the VBox
            txtMensaje.clear();
        } else {
            System.out.println("Error enviando el mensaje: " + respuesta.getMensaje());
        }
    }

    @FXML
    void onActionBtnDeleteChat(ActionEvent event) {
        if (currentChat == null) {
            Mensaje mensaje = new Mensaje();
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "No hay ningún chat seleccionado para eliminar.");
            return;
        }

        // Mostrar una alerta de confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar chat");
        alert.setHeaderText("¿Está seguro de que desea eliminar este chat?");
        alert.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Eliminar el chat usando el servicio
            Respuesta respuesta = chatsService.eliminarChat(currentChat.getChtId());

            if (respuesta.getEstado()) {
                Mensaje mensaje = new Mensaje();
                mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "El chat ha sido eliminado correctamente.");

                // Limpiar la vista y actualizar la tabla de contactos
                currentChat = null;
                vboxChats.getChildren().clear();
                Label noChatLabel = new Label("El chat ha sido eliminado.");
                noChatLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
                vboxChats.getChildren().add(noChatLabel);
            } else {
                Mensaje mensaje = new Mensaje();
                mensaje.show(Alert.AlertType.ERROR, "Error", "Ocurrió un error al eliminar el chat: " + respuesta.getMensaje());
            }
        } else {
            System.out.println("Eliminación de chat cancelada.");
        }
    }


    private void onActionEliminarMensaje(MensajesDTO mensaje) {
        // Confirmación de eliminación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar mensaje");
        alert.setHeaderText("¿Está seguro de que desea eliminar este mensaje?");
        alert.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Llamar al servicio para eliminar el mensaje
            MensajesService mensajesService = new MensajesService();
            Respuesta respuesta = mensajesService.eliminarMensaje(mensaje.getSmsId());

            if (respuesta.getEstado()) {
                Mensaje mensajeAlerta = new Mensaje();
                mensajeAlerta.show(Alert.AlertType.INFORMATION, "Éxito", "El mensaje ha sido eliminado correctamente.");
                currentChat.getMensajesList().remove(mensaje); // Eliminar mensaje de la lista
                mostrarMensajesDelChat(currentChat.getMensajesList()); // Actualizar la vista
            } else {
                Mensaje mensajeAlerta = new Mensaje();
                mensajeAlerta.show(Alert.AlertType.ERROR, "Error", "Ocurrió un error al eliminar el mensaje: " + respuesta.getMensaje());
            }
        }
    }



}