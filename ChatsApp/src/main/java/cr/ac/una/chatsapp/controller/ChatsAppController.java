package cr.ac.una.chatsapp.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cr.ac.una.chatsapp.model.ChatsDTO;
import cr.ac.una.chatsapp.model.MensajesDTO;
import cr.ac.una.chatsapp.model.UsuariosDTO;
import cr.ac.una.chatsapp.service.ChatsService;
import cr.ac.una.chatsapp.util.Respuesta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.util.Callback;

public class ChatsAppController extends Controller implements Initializable {

    @FXML
    private TableView<UsuariosDTO> tbvContactos;
    @FXML
    private TableColumn<UsuariosDTO, String> tbcContactos;
    @FXML
    private VBox vboxChats;

    private ChatsService chatsService = new ChatsService();
    private Long idEmisor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initialize() {
        idEmisor = obtenerIdEmisorActual();
        cargarUsuarios();
        tbvContactos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarChatConUsuario(newValue.getUsuId());
            }
        });
    }

    private Long obtenerIdEmisorActual() {
        return 1L;
    }

    private void cargarUsuarios() {
        Respuesta respuesta = chatsService.getUsuarios();
        if (respuesta.getEstado()) {
            List<UsuariosDTO> usuarios = (List<UsuariosDTO>) respuesta.getResultado("Usuarios");

            // Usar un filtro compatible con versiones anteriores
            List<UsuariosDTO> usuariosActivos = new ArrayList<>();
            for (UsuariosDTO usuario : usuarios) {
                if ("A".equals(usuario.getUsuEstado())) {
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
                                if (usuario.getUsuFoto() != null && usuario.getUsuFoto().length > 0) {
                                    ByteArrayInputStream bis = new ByteArrayInputStream(usuario.getUsuFoto());
                                    imageView.setImage(new Image(bis));
                                } else {
                                    imageView.setImage(new Image("path/to/default/image.png"));
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
                for (ChatsDTO chat : chats) {
                    List<MensajesDTO> mensajes = chat.getMensajesList();
                    if (mensajes != null && !mensajes.isEmpty()) {
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
            } else {
                System.out.println("No hay chats en este chat.");
            }
        } else {
            System.out.println("Error obteniendo los chats: " + respuesta.getMensaje());
        }
    }
}
