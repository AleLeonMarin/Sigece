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
    private MFXTextField txtAsunto;

    @FXML
    private Button btnMaximizeMail;

    @FXML
    private TableColumn<CorreosDTO, String> tbcEstado, tbcDestinatario, tbcPlantilla;

    @FXML
    private TableView<NotificacionDTO> tbvNotificationProcess;

    @FXML
    private TableColumn<NotificacionDTO, String> tbcNotifications;

    private NotificacionService notificacionService = new NotificacionService();
    private CorreosService correosService = new CorreosService();
    private ObservableList<CorreosDTO> correosGenerados = FXCollections.observableArrayList();
    private ObservableList<NotificacionDTO> notificaciones = FXCollections.observableArrayList();
    private NotificacionDTO notificacionSeleccionada;
    private CorreosDTO correoSeleccionado;

    private Mensaje mensaje = new Mensaje();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcNotifications.setCellValueFactory(new PropertyValueFactory<>("notNombre"));


        configurarSeleccionNotificacion();
        configurarSeleccionCorreo();

        cargarNotificaciones();


        tbcDestinatario.setCellValueFactory(new PropertyValueFactory<>("corDestinatario"));
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("corEstado"));
        tbcPlantilla.setCellValueFactory(new PropertyValueFactory<>("corResultado"));
    }

    @Override
    public void initialize() {
        cargarNotificaciones();
        correoSeleccionado = new CorreosDTO();

    }

    private void cargarNotificaciones() {
        Respuesta respuesta = notificacionService.obtenerNotificaciones();
        if (respuesta.getEstado()) {
            List<NotificacionDTO> listaNotificaciones = (List<NotificacionDTO>) respuesta.getResultado("Notificaciones");
            notificaciones.setAll(listaNotificaciones);
            tbvNotificationProcess.setItems(notificaciones);
        } else {
            mensaje.show(Alert.AlertType.ERROR, "Error", "Error al cargar las notificaciones: " + respuesta.getMensaje());
        }
    }

    private void configurarSeleccionNotificacion() {
        tbvNotificationProcess.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                notificacionSeleccionada = newValue;
                cargarPlantillaHTML(newValue);
                btnDowloadExcel.setDisable(false);
                btnUploadExcel.setDisable(false);
            }
        });
    }

    //NO funciona correctamente
    private void configurarSeleccionCorreo() {
        tbvCorreoGenerados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                correoSeleccionado = newValue;
//                cargarPlantillaFinal(newValue);
            }
        });
    }


    private void cargarPlantillaHTML(NotificacionDTO notificacion) {
        if (notificacion != null && notificacion.getNotPlantilla() != null) {
            htmlPreview.getEngine().loadContent(notificacion.getNotPlantilla());
        }
    }


    //NO funciona correctamente
//    private void cargarPlantillaFinal(CorreosDTO correo) {
//        if (correo != null && correo.getCorResultado() != null) {
//            System.out.println("Actualizando WebView con el contenido del correo: " + correo.getCorResultado());
//            webViewPlantillaFinal.getEngine().loadContent(correo.getCorResultado());
//        } else {
//            System.out.println("El correo seleccionado no tiene contenido o es nulo.");
//            webViewPlantillaFinal.getEngine().loadContent("No hay contenido disponible para este correo.</p>");
//        }
//    }


    @FXML
    void onActionBtnUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar archivo Excel");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel", "*.xlsx"));
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                Workbook workbook = WorkbookFactory.create(fis);
                Sheet sheet = workbook.getSheetAt(0);

                correosGenerados.clear();

                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue;

                    CorreosDTO correoDto = new CorreosDTO();

                    Cell correoCell = row.getCell(0);
                    if (correoCell != null) {
                        correoDto.setCorDestinatario(correoCell.getStringCellValue());
                    } else {
                        mensaje.show(Alert.AlertType.WARNING, "Advertencia", "El archivo Excel tiene una fila sin correo.");
                        continue;
                    }

                    if (txtAsunto.getText() != null && !txtAsunto.getText().trim().isEmpty()) {
                        correoDto.setCorAsunto(txtAsunto.getText());
                    } else {
                        correoDto.setCorAsunto(notificacionSeleccionada.getNotNombre());
                    }

                    correoDto.setCorNotId(notificacionSeleccionada);

                    String contenidoHTML = generarContenidoConVariables(row);
                    if (contenidoHTML == null) {
                        mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Una variable condicional no tiene valor.");
                        return;
                    }

                    correoDto.setCorResultado(contenidoHTML);
                    correoDto.setCorEstado("P");
                    correosGenerados.add(correoDto);
                }

                tbvCorreoGenerados.setItems(correosGenerados);

                mensaje.show(Alert.AlertType.INFORMATION, "Carga exitosa", "El archivo Excel se ha procesado correctamente.");

            } catch (IOException e) {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al procesar el archivo Excel: " + e.getMessage());
            }
        }
    }

    @FXML
    void onActionBtnSend(ActionEvent event) {
        correosGenerados.forEach(correo -> {
            Respuesta respuesta = correosService.guardarCorreo(correo);
            if (!respuesta.getEstado()) {
                mensaje.show(Alert.AlertType.ERROR, "Error", "Error al persistir correo de: " + correo.getCorDestinatario());
            }
        });

        tbvCorreoGenerados.refresh();
        mensaje.show(Alert.AlertType.INFORMATION, "Éxito", "Correos enviados a la base de datos, serán enviados automáticamente.");
    }

    private String generarContenidoConVariables(Row row) {
        String plantillaHTML = notificacionSeleccionada.getNotPlantilla();
        String plantillaHTMLFinal = plantillaHTML;
        Sheet sheet = row.getSheet();
        Row headerRow = sheet.getRow(0);
        List<VariablesDTO> variables = notificacionSeleccionada.getSisVariablesList();

        for (int i = 1; i < row.getLastCellNum(); i++) {
            Cell headerCell = headerRow.getCell(i);
            if (headerCell != null) {
                String columnName = headerCell.getStringCellValue();
                String variable = "[" + columnName + "]";

                Cell cell = row.getCell(i);
                String valor = "";

                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            valor = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            valor = "";
                    }
                }

                boolean isCondicional = esVariableCondicional(columnName, variables);

                if (isCondicional && (valor == null || valor.trim().isEmpty())) {
                    mensaje.show(Alert.AlertType.WARNING, "Advertencia", "La variable condicional '" + columnName + "' está vacía. Revisa la estructura de tu Excel.");
                    return null;
                }

                if (!isCondicional && (valor == null || valor.trim().isEmpty())) {
                    valor = buscarValorPorDefecto(columnName, variables);
                    if (valor == null || valor.trim().isEmpty()) {
                        continue;
                    }
                }

                plantillaHTMLFinal = plantillaHTMLFinal.replace(variable, valor);
            }
        }


        for (VariablesDTO variable : variables) {
            String variableName = "[" + variable.getVarNombre() + "]";
            if (plantillaHTMLFinal.contains(variableName)) {
                String defaultValue = variable.getVarValor() != null ? variable.getVarValor() : "Valor no encontrado";
                plantillaHTMLFinal = plantillaHTMLFinal.replace(variableName, defaultValue);
            }
        }

        return plantillaHTMLFinal;
    }

    private String buscarValorPorDefecto(String nombreVariable, List<VariablesDTO> variables) {
        for (VariablesDTO variable : variables) {
            if (variable.getVarNombre().equals(nombreVariable)) {
                return variable.getVarValor() != null ? variable.getVarValor() : "Valor no encontrado";
            }
        }
        return "Valor no encontrado";
    }


    private boolean esVariableCondicional(String columnName, List<VariablesDTO> variables) {
        for (VariablesDTO variable : variables) {
            if (variable.getVarNombre().equals(columnName) && "Condicional".equals(variable.getTipo())) {
                return true;
            }
        }
        return false;
    }


    @FXML
    void onActionBtnDowload(ActionEvent event) {
        if (notificacionSeleccionada == null) {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una notificación.");
            return;
        }

        List<VariablesDTO> variables = notificacionSeleccionada.getSisVariablesList();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Plantilla Notificación");

        Row headerRow = sheet.createRow(0);
        int colIndex = 0;

        Cell emailCell = headerRow.createCell(colIndex++);
        emailCell.setCellValue("Correo Destino");


        for (VariablesDTO variable : variables) {
            Cell cell = headerRow.createCell(colIndex++);
            cell.setCellValue(variable.getVarNombre());
        }

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
    void onActionBtnMaximizeMail(ActionEvent event) {
        if (tbvCorreoGenerados.getSelectionModel().getSelectedItem() != null) {
            String htmlContent = tbvCorreoGenerados.getSelectionModel().getSelectedItem().getCorResultado();
            AppContext.getInstance().set("htmlContent", htmlContent);
            FlowController.getInstance().goViewInWindowModal("MaxViewHTML", this.getStage(), Boolean.TRUE);
        } else {
            mensaje.show(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar una correo.");
        }
    }
}
