package cr.ac.una.mailapp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import cr.ac.una.mailapp.model.CorreosDTO;
import cr.ac.una.mailapp.service.CorreosService;
import cr.ac.una.mailapp.util.AppContext;
import cr.ac.una.mailapp.util.FlowController;
import cr.ac.una.mailapp.util.Mensaje;
import cr.ac.una.mailapp.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuzonController extends Controller implements Initializable {

    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXButton btnSendAgain;

    @FXML
    private MFXButton btnSendNow;

    @FXML
    private MFXButton btnShowContent;

    @FXML
    private MFXButton btnBuscar;


    @FXML
    private Button btnNewMail;

    @FXML
    private MFXFilterComboBox<String> cmbEstado;

    @FXML
    private MFXTextField txtBusqueda;


    @FXML
    private TableColumn<CorreosDTO, String> tbcDestinatario;

    @FXML
    private TableColumn<CorreosDTO, String> tbcEstado;

    @FXML
    private TableColumn<CorreosDTO, String> tbcFecha;

    @FXML
    private TableColumn<CorreosDTO, Long> tbcId;

    @FXML
    private TableView<CorreosDTO> tbvMails;

    private CorreosService correosService;
    private ObservableList<CorreosDTO> correosList;

    private Mensaje mensaje;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        correosService = new CorreosService();
        mensaje = new Mensaje();
        correosList = FXCollections.observableArrayList();
        configurarTabla();

        actualizarCorreos();

        cmbEstado.getItems().addAll("Enviados", "Por enviar", "Todos");


    }

    private void configurarTabla() {
        tbcDestinatario.setCellValueFactory(new PropertyValueFactory<>("corDestinatario"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("corEstado"));
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("corFecha"));
        tbcId.setCellValueFactory(new PropertyValueFactory<>("corId"));

        tbvMails.setItems(correosList);
    }


    private void actualizarCorreos() {
        Respuesta respuesta = correosService.obtenerTodosLosCorreos();
        if (respuesta.getEstado()) {
            List<CorreosDTO> correos = (List<CorreosDTO>) respuesta.getResultado("Correos");
            correosList.setAll(correos);
            tbvMails.refresh();
        }
    }

    @Override
    public void initialize() {
        actualizarCorreos();
    }


    @FXML
    void onActionBtnDelete(ActionEvent event) {
        CorreosDTO correoSeleccionado = tbvMails.getSelectionModel().getSelectedItem();
        if (correoSeleccionado != null) {
            Respuesta respuesta = correosService.eliminarCorreo(correoSeleccionado.getCorId());
            if (!respuesta.getEstado()) {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al eliminar el correo: " + respuesta.getMensaje());
            } else {
                tbvMails.getItems().remove(correoSeleccionado); // Remover el correo de la tabla
                mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "El correo ha sido eliminado correctamente.");
            }
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar un correo para eliminar.");
        }
    }


//    @FXML
//    void onActionBtnSendAgain(ActionEvent event) {
//        CorreosDTO correoSeleccionado = tbvMails.getSelectionModel().getSelectedItem();
//
//        if (correoSeleccionado != null) {
//            correoSeleccionado.setCorEstado("P");
//
//            Respuesta respuesta = correosService.guardarCorreo(correoSeleccionado);
//
//            if (respuesta.getEstado()) {
//                mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "El estado del correo ha sido cambiado a 'Por enviar'.");
//                tbvMails.refresh();
//            } else {
//                mensaje.show(Alert.AlertType.ERROR, "Error", "Hubo un error al actualizar el estado del correo: " + respuesta.getMensaje());
//            }
//        } else {
//            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar un correo para reenviar.");
//        }
//    }


    @FXML
    void onActionBtnSendNow(ActionEvent event) {
       actualizarCorreos();
    }


    @FXML
    void onActionBtnShowContent(ActionEvent event) {
        CorreosDTO correoSeleccionado = tbvMails.getSelectionModel().getSelectedItem();
        if (correoSeleccionado != null && correoSeleccionado.getCorResultado() != null) {
            String htmlContent = correoSeleccionado.getCorResultado();
            AppContext.getInstance().set("htmlContent", htmlContent);

            FlowController.getInstance().goViewInWindowModal("MaxViewHTML", this.getStage(), Boolean.TRUE);
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar un correo con contenido para mostrar.");
        }
    }


    @FXML
    void onActionBtnBuscar(ActionEvent event) {
        String terminoBusqueda = txtBusqueda.getText().trim().toLowerCase();
        String estadoSeleccionado = cmbEstado.getSelectionModel().getSelectedItem();

        if (estadoSeleccionado != null && estadoSeleccionado.equalsIgnoreCase("Enviados")) {
            estadoSeleccionado = "E";
        } else if (estadoSeleccionado != null && estadoSeleccionado.equalsIgnoreCase("Por enviar")) {
            estadoSeleccionado = "P";
        }else {
            estadoSeleccionado = null;
        }

        String finalEstadoSeleccionado = estadoSeleccionado;
        List<CorreosDTO> correosFiltrados = correosList.stream()
                .filter(correo -> (terminoBusqueda.isEmpty() || correo.getCorDestinatario().toLowerCase().contains(terminoBusqueda)
                        || correo.getCorEstado().toLowerCase().contains(terminoBusqueda)
                        || String.valueOf(correo.getCorId()).contains(terminoBusqueda)))
                .filter(correo -> finalEstadoSeleccionado == null || correo.getCorEstado().equalsIgnoreCase(finalEstadoSeleccionado))
                .collect(Collectors.toList());

        tbvMails.setItems(FXCollections.observableArrayList(correosFiltrados));
        tbvMails.refresh();
    }

    @FXML
    void onActionBtnNewMail(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("EnvioCorreoView");
    }


}
