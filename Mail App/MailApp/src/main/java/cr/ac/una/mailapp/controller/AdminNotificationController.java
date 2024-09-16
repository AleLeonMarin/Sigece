package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.model.NotificacionDTO;
import cr.ac.una.mailapp.service.NotificacionService;
import cr.ac.una.mailapp.util.Respuesta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminNotificationController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextArea plantillaCode;

    @FXML
    private WebView plantillaPreview;

    @FXML
    private TableColumn<NotificacionDTO, Long> tbcId;

    @FXML
    private TableColumn<NotificacionDTO, String> tbcNombre;

    @FXML
    private TableView<NotificacionDTO> tbvProcesosNotificacion;

    private NotificacionService notificacionService;
    private ObservableList<NotificacionDTO> notificaciones;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notificacionService = new NotificacionService();


        tbcId.setCellValueFactory(new PropertyValueFactory<>("notId"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("notNombre"));


        cargarNotificaciones();

        tbvProcesosNotificacion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarPlantilla(newValue);
            }
        });


        plantillaCode.setOnKeyReleased(event -> updatePreview());
    }
    @Override
    public void initialize() {

    }

    // Método para actualizar la vista previa en el WebView
    private void updatePreview() {
        String htmlCode = plantillaCode.getText();
        plantillaPreview.getEngine().loadContent(htmlCode); // Carga el HTML en la WebView
    }

    private void cargarPlantilla(NotificacionDTO notificacion) {

        plantillaCode.setText(notificacion.getNotPlantilla());
        updatePreview();
    }


    private void cargarNotificaciones() {
        NotificacionService notificacionService = new NotificacionService();

        Respuesta respuesta = notificacionService.obtenerNotificaciones();

        if (respuesta.getEstado()) {
            List<NotificacionDTO> notificaciones = (List<NotificacionDTO>) respuesta.getResultado("Notificaciones");


            tbvProcesosNotificacion.getItems().clear();
            tbvProcesosNotificacion.getItems().addAll(notificaciones);
        } else {
            System.out.println("Error al cargar las notificaciones: " + respuesta.getMensaje());
        }
    }
}
