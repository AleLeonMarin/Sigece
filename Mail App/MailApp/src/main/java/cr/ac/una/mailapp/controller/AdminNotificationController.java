package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.model.NotificacionDTO;
import cr.ac.una.mailapp.model.VariablesDTO;
import cr.ac.una.mailapp.service.NotificacionService;
import cr.ac.una.mailapp.service.VariablesService;
import cr.ac.una.mailapp.util.Mensaje;
import cr.ac.una.mailapp.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private MFXTextField txtVarNombre;

    @FXML
    private MFXComboBox<String> txtVarTipo;

    @FXML
    private MFXTextField txtVarValor;

    @FXML
    private TableView<NotificacionDTO> tbvProcesosNotificacion;

    @FXML
    private TableColumn<NotificacionDTO, Long> tbcId;

    @FXML
    private TableColumn<NotificacionDTO, String> tbcNombre;

    @FXML
    private TableView<VariablesDTO> tbvVariables;

    @FXML
    private TableColumn<VariablesDTO, String> tbcVarName;

    @FXML
    private TableColumn<VariablesDTO, String> tbcContent;

    @FXML
    private TableColumn<VariablesDTO, String> tbcType;

    @FXML
    private Button btnSaveBar;

    @FXML
    private Button btnNewVar;

    @FXML
    private Button btnDeleteVar;

    @FXML
    private WebView plantillaPreviewFinal;

    private NotificacionService notificacionService;
    private VariablesService variablesService;
    private ObservableList<NotificacionDTO> notificaciones;
    private NotificacionDTO notificacionSeleccionada;
    private VariablesDTO variableSeleccionada;
    private Mensaje mensaje;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notificacionService = new NotificacionService();
        variablesService = new VariablesService();
        mensaje = new Mensaje();

        tbcId.setCellValueFactory(new PropertyValueFactory<>("notId"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("notNombre"));
        tbcVarName.setCellValueFactory(new PropertyValueFactory<>("varNombre"));
        tbcContent.setCellValueFactory(new PropertyValueFactory<>("varValor"));
        tbcType.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        cargarNotificaciones();

        tbvProcesosNotificacion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                notificacionSeleccionada = newValue;
                cargarPlantilla(newValue);
                cargarVariables();
            }
        });

        tbvVariables.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                variableSeleccionada = newValue;
                cargarVariableSeleccionada();
            }
        });

        ObservableList<String> opciones = FXCollections.observableArrayList("D", "C");
        txtVarTipo.setItems(opciones);

        plantillaCode.setOnKeyReleased(event -> updatePreview());
    }

    @Override
    public void initialize() {
        cargarNotificaciones();
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

    private void cargarVariables() {
        if (notificacionSeleccionada != null) {
            Respuesta respuesta = variablesService.obtenerVariablesPorNotificacion(notificacionSeleccionada.getNotId());

            if (respuesta.getEstado()) {
                List<VariablesDTO> variablesList = (List<VariablesDTO>) respuesta.getResultado("Variables");
                ObservableList<VariablesDTO> variablesObservableList = FXCollections.observableArrayList(variablesList);
                tbvVariables.setItems(variablesObservableList);
            } else {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al cargar las variables: " + respuesta.getMensaje());
            }
        }
    }

    private void cargarVariableSeleccionada() {
        if (variableSeleccionada != null) {
            txtVarNombre.setText(variableSeleccionada.getVarNombre());
            txtVarValor.setText(variableSeleccionada.getVarValor());
            txtVarTipo.setValue(variableSeleccionada.getTipo() != null ? variableSeleccionada.getTipo() : "");
        }
    }

    private void cargarNotificaciones() {
        Respuesta respuesta = notificacionService.obtenerNotificaciones();

        if (respuesta.getEstado()) {
            List<NotificacionDTO> notificaciones = (List<NotificacionDTO>) respuesta.getResultado("Notificaciones");
            tbvProcesosNotificacion.getItems().clear();
            tbvProcesosNotificacion.getItems().addAll(notificaciones);
            tbvVariables.getItems().clear();
        } else {
            mensaje.show(Alert.AlertType.ERROR, "Error", "Error al cargar las notificaciones: " + respuesta.getMensaje());
        }
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {
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
    void onActionBtnNuevo(ActionEvent event) {
        limpiarFormulario();
    }

    @FXML
    void onActionBtnSave(ActionEvent event) {
        if (txtNombre.getText().isEmpty() || plantillaCode.getText().isEmpty()) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe completar todos los campos.");
            return;
        }

        NotificacionDTO notificacion = notificacionSeleccionada != null ? notificacionSeleccionada : new NotificacionDTO();
        notificacion.setNotNombre(txtNombre.getText());
        notificacion.setNotPlantilla(plantillaCode.getText());
        
        //todo

        Respuesta respuesta = notificacionService.guardarNotificacion(notificacion);

        if (respuesta.getEstado()) {
            mensaje.show(Alert.AlertType.INFORMATION, "Éxito", notificacionSeleccionada != null ? "Notificación actualizada correctamente." : "Notificación guardada correctamente.");
            cargarNotificaciones();
            limpiarFormulario();
        } else {
            mensaje.show(Alert.AlertType.ERROR, "Error", "Error al guardar la notificación: " + respuesta.getMensaje());
        }
    }

    @FXML
    void onActionBtnSaveVar(ActionEvent event) {
        if (txtVarNombre.getText().isEmpty() || txtVarTipo.getValue().isEmpty()) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe completar los campos de la variable.");
            return;
        }

        VariablesDTO variableAEditar;

        if (variableSeleccionada != null) {
            variableAEditar = variableSeleccionada;
        } else {
            variableAEditar = new VariablesDTO();
        }

        variableAEditar.setVarNombre(txtVarNombre.getText());
        variableAEditar.setVarValor(txtVarValor.getText());
        variableAEditar.setTipo(txtVarTipo.getValue());


        if (notificacionSeleccionada != null) {
            variableAEditar.setVarNotId(notificacionSeleccionada);  // Asignar notificación seleccionada
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una notificación válida.");
            return;
        }

        // Intentar guardar la variable
        Respuesta respuesta = variablesService.guardarVariable(variableAEditar);

        if (respuesta.getEstado()) {
            mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "Variable guardada correctamente.");

            if (variableSeleccionada != null) {
                tbvVariables.refresh();  // Refrescar la tabla si se está editando
            } else {
                tbvVariables.getItems().add(variableAEditar);  // Añadir la nueva variable si es nueva
            }

            limpiarFormularioVar();
        } else {
            mensaje.show(Alert.AlertType.ERROR, "Error", "Error al guardar la variable: " + respuesta.getMensaje());
        }
    }



    @FXML
    void onActionBtnDeleteVar(ActionEvent event) {
        if (variableSeleccionada != null) {
            boolean confirm = mensaje.showConfirmation("Eliminar Variable", root.getScene().getWindow(), "¿Está seguro de eliminar esta variable?");
            if (confirm) {
                Respuesta respuesta = variablesService.eliminarVariable(variableSeleccionada.getVarId());

                if (respuesta.getEstado()) {
                    mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "Variable eliminada correctamente.");
                    cargarVariables();
                    limpiarFormularioVar();
                } else {
                    mensaje.show(Alert.AlertType.ERROR, "Error", "Error al eliminar la variable: " + respuesta.getMensaje());
                }
            }
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una variable para eliminar.");
        }
    }

    @FXML
    void onActionBtnNewVar(ActionEvent event) {
        limpiarFormularioVar();
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        plantillaCode.clear();
        notificacionSeleccionada = null;
        updatePreview();
    }

    private void limpiarFormularioVar() {
        txtVarNombre.clear();
        txtVarValor.clear();
        txtVarTipo.clear();
        variableSeleccionada = null;
    }
}
