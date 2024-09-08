package cr.ac.una.chatsapp.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cr.ac.una.chatsapp.model.MensajesDTO;
import cr.ac.una.chatsapp.model.UsuariosDTO;
import cr.ac.una.chatsapp.service.ChatsService;
import cr.ac.una.chatsapp.util.Respuesta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

public class ChatsAppController extends Controller implements Initializable {

    @FXML
    private TableView<UsuariosDTO> tbvContactos;
    @FXML
    private TableColumn<UsuariosDTO, String> tbcContactos;

    private ChatsService chatsService = new ChatsService();

    @FXML
    private VBox vboxChats;

    private Long idEmisor;  // ID del emisor (usuario que está usando la aplicación)

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Obtener el ID del usuario actual (emisor)
        idEmisor = obtenerIdEmisorActual();

        cargarUsuarios();

        // Listener para detectar selección de usuario y cargar el chat correspondiente
        tbvContactos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarChatConUsuario(newValue.getUsuId()); // Cargar el chat con el usuario seleccionado
            }
        });
    }

    @Override
    public void initialize() {

    }

    private Long obtenerIdEmisorActual() {
        // Método ficticio para obtener el ID del usuario que está usando la aplicación
        return 1L; // Supongamos que el ID del emisor es 1
    }

    private void cargarUsuarios() {
        Respuesta respuesta = chatsService.getUsuarios();
        if (respuesta.getEstado()) {
            List<UsuariosDTO> usuarios = (List<UsuariosDTO>) respuesta.getResultado("Usuarios");
            ObservableList<UsuariosDTO> usuariosList = FXCollections.observableArrayList(usuarios);
            tbvContactos.setItems(usuariosList);

            tbcContactos.setCellFactory(new Callback<TableColumn<UsuariosDTO, String>, TableCell<UsuariosDTO, String>>() {
                @Override
                public TableCell<UsuariosDTO, String> call(TableColumn<UsuariosDTO, String> param) {
                    return new TableCell<UsuariosDTO, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                                setGraphic(null);
                            } else {
                                UsuariosDTO usuario = getTableRow().getItem();

                                HBox hbox = new HBox(10);

                                ImageView imageView = new ImageView();
                                if (usuario.getUsuFoto() != null && usuario.getUsuFoto().length > 0) {
                                    ByteArrayInputStream bis = new ByteArrayInputStream(usuario.getUsuFoto());
                                    Image image = new Image(bis);
                                    imageView.setImage(image);
                                } else {
                                    imageView.setImage(new Image("path/to/default/image.png")); // Imagen por defecto
                                }
                                imageView.setFitHeight(40);
                                imageView.setFitWidth(40);

                                Label nombreLabel = new Label(usuario.getUsuNombre() + " " + usuario.getUsuApellidos());
                                Label estadoLabel = new Label(usuario.getUsuStatus());

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
            List<MensajesDTO> mensajes = (List<MensajesDTO>) respuesta.getResultado("Mensajes");

            if (mensajes != null && !mensajes.isEmpty()) {  // Verifica si la lista no es nula ni está vacía
                for (MensajesDTO mensaje : mensajes) {
                    HBox hbox = new HBox();
                    Label mensajeLabel = new Label(mensaje.getSmsTexto());

                    if (mensaje.getEmisorId().equals(idEmisor)) {
                        hbox.setAlignment(Pos.CENTER_RIGHT);
                        mensajeLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 10px; -fx-background-radius: 10px;");
                    } else {
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        mensajeLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-background-radius: 10px;");
                    }

                    hbox.getChildren().add(mensajeLabel);
                    vboxChats.getChildren().add(hbox);
                }
            } else {
                System.out.println("No hay mensajes en este chat.");
            }
        } else {
            System.out.println("Error obteniendo los mensajes: " + respuesta.getMensaje());
        }
    }

}
