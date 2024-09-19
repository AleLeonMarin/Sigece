package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.model.CorreosDTO;
import cr.ac.una.mailapp.model.NotificacionDTO;
import cr.ac.una.mailapp.model.VariablesDTO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MassiveMailSenderController extends Controller implements Initializable {

    @FXML
    private Button btnUploadExcel, btnSendMails, btnEnviarCorreosFinales, btnDowloadExcel, btnMaximize;

    @FXML
    private WebView webViewPlantillaFinal, htmlPreview;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CorreosDTO> tbvCorreoGenerados;

    @FXML
    private TableColumn<CorreosDTO, String> tbcEstado, tbcDestinatario, tbcPlantilla;

    @FXML
    private TableView<NotificacionDTO> tbvNotificationProcess; // Tabla para mostrar las notificaciones

    @FXML
    private TableColumn<NotificacionDTO, String> tbcNotifications; // Columna que mostrará los nombres de las notificaciones

    private NotificacionService notificacionService = new NotificacionService();
    private CorreosService correosService = new CorreosService();
    private ObservableList<CorreosDTO> correosGenerados = FXCollections.observableArrayList();
    private ObservableList<NotificacionDTO> notificaciones = FXCollections.observableArrayList(); // Lista de notificaciones
    private NotificacionDTO notificacionSeleccionada;

    private Mensaje mensaje = new Mensaje();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar las columnas de la tabla

        tbcNotifications.setCellValueFactory(new PropertyValueFactory<>("notNombre"));


        cargarNotificaciones();
        configurarSeleccionNotificacion();



        tbcDestinatario.setCellValueFactory(new PropertyValueFactory<>("corDestinatario"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("corEstado"));
        tbcPlantilla.setCellValueFactory(new PropertyValueFactory<>("corResultado"));

        tbvCorreoGenerados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarPlantillaFinal(newValue);
            }
        });


    }

    @Override
    public void initialize() {
        cargarNotificaciones();
    }

    private void cargarNotificaciones() {
        // Obtener notificaciones del servicio
        Respuesta respuesta = notificacionService.obtenerNotificaciones();
        if (respuesta.getEstado()) {
            List<NotificacionDTO> listaNotificaciones = (List<NotificacionDTO>) respuesta.getResultado("Notificaciones");
            notificaciones.setAll(listaNotificaciones); // Agregar las notificaciones a la lista observable
            tbvNotificationProcess.setItems(notificaciones); // Asociar la lista a la tabla
        } else {
            mensaje.show(Alert.AlertType.ERROR, "Error", "Error al cargar las notificaciones: " + respuesta.getMensaje());
        }
    }

    private void configurarSeleccionNotificacion() {
        // Escuchar los cambios en la selección de la tabla de notificaciones
        tbvNotificationProcess.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                notificacionSeleccionada = newValue; // Asignar la notificación seleccionada
                cargarPlantillaHTML(newValue); // Cargar la plantilla HTML correspondiente a la notificación seleccionada
            }
        });
    }

    private void cargarPlantillaHTML(NotificacionDTO notificacion) {
        // Mostrar la plantilla de la notificación en el WebView
        if (notificacion != null && notificacion.getNotPlantilla() != null) {
            htmlPreview.getEngine().loadContent(notificacion.getNotPlantilla());
        }
    }

    private void cargarPlantillaFinal(CorreosDTO correo) {
        // Mostrar la plantilla generada con variables reemplazadas en el WebView
        if (correo != null && correo.getCorResultado() != null) {
            webViewPlantillaFinal.getEngine().loadContent(correo.getCorResultado());
        }
    }

    @FXML
    void onActionBtnUpload(ActionEvent event) {
        // Cargar el archivo Excel
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar archivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                Workbook workbook = WorkbookFactory.create(fis);
                Sheet sheet = workbook.getSheetAt(0);

                correosGenerados.clear(); // Limpiar correos previos

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue; // Saltar encabezado

                    CorreosDTO correoDto = new CorreosDTO();
                    correoDto.setCorDestinatario(row.getCell(2).getStringCellValue()); // El correo del destinatario está en la columna 2
                    correoDto.setCorAsunto(notificacionSeleccionada.getNotNombre());  // Asunto basado en la notificación
                    correoDto.setCorNotId(notificacionSeleccionada); // Asociar notificación

                    String contenidoHTML = generarContenidoConVariables(row);
                    correoDto.setCorResultado(contenidoHTML); // Plantilla con variables reemplazadas
                    correoDto.setCorEstado("P"); // Estado "Por enviar"
                    correosGenerados.add(correoDto);
                }

                tbvCorreoGenerados.setItems(correosGenerados); // Mostrar correos generados
                mensaje.show(Alert.AlertType.INFORMATION, "Carga exitosa", "El archivo Excel se ha procesado correctamente.");

            } catch (IOException e) {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al procesar el archivo Excel: " + e.getMessage());
            }
        }
    }

    @FXML
    void onActionBtnSend(ActionEvent event) {
        correosGenerados.forEach(correo -> {
            Respuesta respuesta = correosService.enviarCorreo(correo);

            if (respuesta.getEstado()) {
                correo.setCorEstado("E"); // Cambiar estado a Enviado
            } else {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al enviar correo a: " + correo.getCorDestinatario());
            }
        });

        tbvCorreoGenerados.refresh(); // Actualizar tabla
    }

    private String generarContenidoConVariables(Row row) {
        // Generar contenido del correo reemplazando las variables en la plantilla HTML
        String plantillaHTML = notificacionSeleccionada.getNotPlantilla();
        for (int i = 1; i < row.getLastCellNum(); i++) {
            String variable = "${var" + i + "}";
            String valor = row.getCell(i) != null ? row.getCell(i).toString() : "Valor por defecto";
            plantillaHTML = plantillaHTML.replace(variable, valor);
        }
        return plantillaHTML;
    }

    @FXML
    void onActionBtnDowload(ActionEvent event) {
        // Asegurarnos de que haya una notificación seleccionada
        if (notificacionSeleccionada == null) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una notificación.");
            return;
        }

        // Obtener las variables asociadas a la notificación
        List<VariablesDTO> variables = notificacionSeleccionada.getSisVariablesList();

        // Crear archivo Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Plantilla Notificación");

        // Crear fila de encabezado
        Row headerRow = sheet.createRow(0);
        int colIndex = 0;
        for (VariablesDTO variable : variables) {
            Cell cell = headerRow.createCell(colIndex++);
            cell.setCellValue(variable.getVarNombre());
        }

        // Añadir una columna extra para el correo electrónico
        Cell emailCell = headerRow.createCell(colIndex);
        emailCell.setCellValue("Correo Destino");

        // Abrir diálogo de guardar archivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Plantilla Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                workbook.close();
                mensaje.show(Alert.AlertType.INFORMATION, "Plantilla Excel guardada", "La plantilla Excel se ha guardado correctamente.");
            } catch (IOException e) {
                mensaje.show(Alert.AlertType.ERROR, "Error al guardar Excel", "Hubo un error al escribir el archivo Excel: " + e.getMessage());
            }
        }
    }

    @FXML
    void onActionBtnMaximize(ActionEvent event) {
        if (notificacionSeleccionada != null) {
            String htmlContent = notificacionSeleccionada.getNotPlantilla();
            AppContext.getInstance().set("htmlContent", htmlContent);
            FlowController.getInstance().goViewInWindowModal("MaxViewHTML", this.getStage(), Boolean.TRUE);
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una notificación.");
        }
    }

    @FXML
    void onActionBtnEnviarCorreos(ActionEvent event) {
        if (correosGenerados.isEmpty()) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "No hay correos generados para enviar.");
        } else {
            correosGenerados.forEach(correo -> {
                if ("P".equals(correo.getCorEstado())) {
                    Respuesta respuesta = correosService.enviarCorreo(correo);
                    if (respuesta.getEstado()) {
                        correo.setCorEstado("E");
                    } else {
                        mensaje.show(Alert.AlertType.ERROR, "Error", "Error al enviar correo a: " + correo.getCorDestinatario());
                    }
                }
            });
            tbvCorreoGenerados.refresh(); // Actualizar la tabla para reflejar cambios
        }
    }
}
