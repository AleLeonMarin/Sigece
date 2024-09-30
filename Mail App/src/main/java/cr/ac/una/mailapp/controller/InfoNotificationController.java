package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.model.CorreosDTO;
import cr.ac.una.mailapp.model.NotificacionDTO;
import cr.ac.una.mailapp.service.CorreosService;
import cr.ac.una.mailapp.service.NotificacionService;
import cr.ac.una.mailapp.util.AppContext;
import cr.ac.una.mailapp.util.FlowController;
import cr.ac.una.mailapp.util.Mensaje;
import cr.ac.una.mailapp.util.Respuesta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InfoNotificationController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<CorreosDTO, String> tbcDestinatario;

    @FXML
    private TableView<CorreosDTO> tbvMails;


    @FXML
    private Button btnMaximize;

    @FXML
    private Label txtPorEnviar;

    @FXML
    private Label txtVecesEnviado;

    @FXML
    private Label txtVecesUtilizado;

    private NotificacionDTO notificacionSeleccionada;
    private CorreosService correosService;

    private Mensaje mensaje = new Mensaje();

    private NotificacionService notificacionService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void initialize() {
        correosService = new CorreosService();
        cargarNotificacion();
        tbcDestinatario.setCellValueFactory(new PropertyValueFactory<>("corDestinatario"));
    }

    private void cargarNotificacion() {
        notificacionSeleccionada = (NotificacionDTO) AppContext.getInstance().get("notificacionSeleccionada");

        if (notificacionSeleccionada != null) {
            txtVecesUtilizado.setText(String.valueOf(notificacionSeleccionada.getSisCorreosList().size()));

            List<CorreosDTO> correosList = notificacionSeleccionada.getSisCorreosList();
            ObservableList<CorreosDTO> correosObservableList = FXCollections.observableArrayList(correosList);
            tbvMails.setItems(correosObservableList);

            long porEnviar = correosList.stream().filter(correo -> correo.getCorEstado().equals("P")).count();
            long enviados = correosList.stream().filter(correo -> correo.getCorEstado().equals("E")).count();

            txtPorEnviar.setText(String.valueOf(porEnviar));
            txtVecesEnviado.setText(String.valueOf(enviados));
        } else {
            mensaje.show(Alert.AlertType.ERROR, "Error", "No se ha seleccionado ninguna notificación.");
        }
    }


    @FXML
    void onActionBtnMaximize(ActionEvent event) {

        String htmlContent = tbvMails.getSelectionModel().getSelectedItem().getCorResultado();
        AppContext.getInstance().set("htmlContent", htmlContent);
        FlowController.getInstance().goViewInWindowModal("MaxViewHTML", this.getStage(), Boolean.TRUE);

    }



}

