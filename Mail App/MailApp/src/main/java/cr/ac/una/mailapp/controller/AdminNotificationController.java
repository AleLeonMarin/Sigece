package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.model.NotificacionDTO;
import cr.ac.una.mailapp.model.VariablesDTO;
import cr.ac.una.mailapp.service.NotificacionService;
import cr.ac.una.mailapp.service.VariablesService;
import cr.ac.una.mailapp.util.AppContext;
import cr.ac.una.mailapp.util.FlowController;
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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
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
    private TableView<VariablesDTO> tbvVariables2;

    @FXML
    private TableColumn<VariablesDTO, String> tbcVariables2;

    @FXML
    private TableColumn<VariablesDTO, String> tbcVariablesTipo2;

    @FXML
    private Button btnSaveBar;

    @FXML
    private Button btnNewVar;

    @FXML
    private Button btnDeleteVar;

    @FXML
    private Button btnMaximazeView;

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

        tbcVariables2.setCellValueFactory(new PropertyValueFactory<>("varNombre"));
        tbcVariablesTipo2.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        setupDoubleClickForVariables();

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

        tbvVariables2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue instanceof VariablesDTO) {
                VariablesDTO variableSeleccionada = (VariablesDTO) newValue;
                insertarVariableEnHTML(variableSeleccionada.getVarNombre());
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
                tbvVariables2.setItems(variablesObservableList);
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
            tbvVariables.getItems().clear(); // Limpiar la tabla de variables
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

        ObservableList<VariablesDTO> variablesList = tbvVariables.getItems();
        String htmlContent = plantillaCode.getText();

        for (VariablesDTO variable : variablesList) {
            String variableName = variable.getVarNombre();
            if (!htmlContent.contains(variableName)) {
                mensaje.show(Alert.AlertType.WARNING, "Advertencia", "La variable '" + variable.getVarNombre() + "' no está implementada en la plantilla HTML.");
                return;
            }
        }

        NotificacionDTO notificacion = notificacionSeleccionada != null ? notificacionSeleccionada : new NotificacionDTO();
        notificacion.setNotNombre(txtNombre.getText());
        notificacion.setNotPlantilla(htmlContent);
        notificacion.setSisVariablesList(new ArrayList<>(variablesList));

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
        if (txtVarNombre.getText().isEmpty() || txtVarTipo.getValue() == null || txtVarTipo.getValue().isEmpty()) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe completar los campos de nombre y tipo de la variable.");
            return;
        }

        if (txtVarTipo.getValue().equals("C") && !txtVarValor.getText().isEmpty()) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Las variables condicionales no deben tener contenido en el campo de valor.");
            return;
        }

        VariablesDTO variableAEditar;


        if (variableSeleccionada != null) {
            variableAEditar = variableSeleccionada;
        } else {
            variableAEditar = new VariablesDTO();
        }


        variableAEditar.setVarNombre(txtVarNombre.getText());
        variableAEditar.setTipo(txtVarTipo.getValue());

        if (!txtVarTipo.getValue().equals("C")) {
            variableAEditar.setVarValor(txtVarValor.getText());
        } else {
            variableAEditar.setVarValor("");
        }

        if (notificacionSeleccionada != null) {
            variableAEditar.setVarNotId(notificacionSeleccionada);  // Relacionar con la notificación seleccionada
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una notificación válida.");
            return;
        }

        if (variableSeleccionada == null) {
            if (!tbvVariables.getItems().contains(variableAEditar)) {
                tbvVariables.getItems().add(variableAEditar);
            }

            if (!tbvVariables2.getItems().contains(variableAEditar)) {
                tbvVariables2.getItems().add(variableAEditar);
            }
        } else {
            tbvVariables.refresh();
            tbvVariables2.refresh();
        }

        limpiarFormularioVar();
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
        txtVarNombre.requestFocus();
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

    private void setupDoubleClickForVariables() {
        // Hacer que las filas de tbvVariables2 respondan al doble clic
        tbvVariables2.setRowFactory(tv -> {
            TableRow<VariablesDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    VariablesDTO selectedVariable = row.getItem();
                    insertarVariableEnHTML(selectedVariable.getVarNombre());
                }
            });
            return row;
        });
    }


    private void insertarVariableEnHTML(String variable) {
        String currentHTML = plantillaCode.getText();
        int cursorPosition = plantillaCode.getCaretPosition(); // Obtener la posición actual del cursor

        String updatedHTML = currentHTML.substring(0, cursorPosition) + variable + currentHTML.substring(cursorPosition);

        plantillaCode.setText(updatedHTML);
        plantillaCode.positionCaret(cursorPosition + variable.length());
        updatePreview();
    }


    @FXML
    void onActionBtnMax(ActionEvent event) {
        String htmlContent = plantillaCode.getText();
        AppContext.getInstance().set("htmlContent", htmlContent);
        FlowController.getInstance().goViewInWindowModal("MaxViewHTML", this.getStage(), Boolean.TRUE);
    }

}
