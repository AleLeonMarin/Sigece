package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.model.NotificacionDTO;
import cr.ac.una.mailapp.service.NotificacionService;
import cr.ac.una.mailapp.util.Mensaje;
import cr.ac.una.mailapp.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminNotificationController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnSave;

    @FXML
    private TextArea plantillaCode;

    @FXML
    private WebView plantillaPreview;

    @FXML
    private MFXTextField txtNombre;

    @FXML
    private TableColumn<NotificacionDTO, Long> tbcId;

    @FXML
    private TableColumn<NotificacionDTO, String> tbcNombre;

    @FXML
    private TableView<NotificacionDTO> tbvProcesosNotificacion;

    @FXML
    private WebView plantillaPreviewFinal;

    private NotificacionService notificacionService;
    private ObservableList<NotificacionDTO> notificaciones;
    private NotificacionDTO notificacionSeleccionada;
    private Mensaje mensaje;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notificacionService = new NotificacionService();
        mensaje = new Mensaje();

        tbcId.setCellValueFactory(new PropertyValueFactory<>("notId"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("notNombre"));

        cargarNotificaciones();

        tbvProcesosNotificacion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                notificacionSeleccionada = newValue;
                cargarPlantilla(newValue);
            }
        });

        plantillaCode.setOnKeyReleased(event -> updatePreview());
    }

    @Override
    public void initialize() {

    }

    private void updatePreview() {
        String htmlCode = plantillaCode.getText();
        plantillaPreview.getEngine().loadContent(htmlCode);
        plantillaPreviewFinal.getEngine().loadContent(htmlCode);
    }

    private void cargarPlantilla(NotificacionDTO notificacion) {
        plantillaCode.setText(notificacion.getNotPlantilla());
        txtNombre.setText(notificacion.getNotNombre());
        updatePreview();
    }

    private void cargarNotificaciones() {
        Respuesta respuesta = notificacionService.obtenerNotificaciones();

        if (respuesta.getEstado()) {
            List<NotificacionDTO> notificaciones = (List<NotificacionDTO>) respuesta.getResultado("Notificaciones");
            tbvProcesosNotificacion.getItems().clear();
            tbvProcesosNotificacion.getItems().addAll(notificaciones);
        } else {
            mensaje.show(Alert.AlertType.ERROR, "Error", "Error al cargar las notificaciones: " + respuesta.getMensaje());
        }
    }

    @FXML
    void onActionBtnDelete(javafx.event.ActionEvent event) {
        if (notificacionSeleccionada != null) {
            boolean confirm = mensaje.showConfirmation("Eliminar Notificación", root.getScene().getWindow(), "¿Está seguro de eliminar esta notificación?");
            if (confirm) {
                Respuesta respuesta = notificacionService.eliminarNotificacion(notificacionSeleccionada.getNotId());

                if (respuesta.getEstado()) {
                    mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "Notificación eliminada correctamente.");
                    cargarNotificaciones();
                    limpiarFormulario();
                } else {
                    mensaje.show(Alert.AlertType.ERROR, "Error", "Error al eliminar la notificación: " + respuesta.getMensaje());
                }
            }
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una notificación para eliminar.");
        }
    }

    @FXML
    void onActionBtnNuevo(javafx.event.ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    void onActionBtnSave(javafx.event.ActionEvent event) {
        if (txtNombre.getText().isEmpty() || plantillaCode.getText().isEmpty()) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe completar todos los campos.");
            return;
        }

        if (notificacionSeleccionada != null) {
            notificacionSeleccionada.setNotNombre(txtNombre.getText());
            notificacionSeleccionada.setNotPlantilla(plantillaCode.getText());

            // Llama al servicio para actualizar la notificación
            Respuesta respuesta = notificacionService.guardarNotificacion(notificacionSeleccionada);

            if (respuesta.getEstado()) {
                mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "Notificación actualizada correctamente.");
                cargarNotificaciones();
                limpiarFormulario();
            } else {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al actualizar la notificación: " + respuesta.getMensaje());
            }

        } else {
            NotificacionDTO nuevaNotificacion = new NotificacionDTO();
            nuevaNotificacion.setNotNombre(txtNombre.getText());
            nuevaNotificacion.setNotPlantilla(plantillaCode.getText());

            Respuesta respuesta = notificacionService.guardarNotificacion(nuevaNotificacion);

            if (respuesta.getEstado()) {
                mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "Notificación guardada correctamente.");
                cargarNotificaciones(); // Actualizar la lista de notificaciones
                limpiarFormulario();
            } else {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al guardar la notificación: " + respuesta.getMensaje());
            }
        }
    }


    private void limpiarFormulario() {
        txtNombre.clear();
        plantillaCode.clear();
        notificacionSeleccionada = null;
        updatePreview();
    }
}
