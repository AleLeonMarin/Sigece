package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.sarxos.webcam.Webcam;

import cr.ac.una.tarea.util.WebCam;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class CamController extends Controller implements Initializable {

    @FXML
    private MFXButton btnActivate;

    @FXML
    private MFXButton btnStop;

    @FXML
    private MFXButton btnTake;

    @FXML
    private ImageView imgvCam;

    WebCam webCam;

    @FXML
    void onActionBtnActivate(ActionEvent event) {
        webCam = new WebCam(imgvCam);

        webCam.start();

        webCam.updateImageView();
    }

    @FXML
    void onActionBtnStop(ActionEvent event) {

        webCam.stop();

    }

    @FXML
    void onActionBtnTake(ActionEvent event) {

        webCam.takePhoto();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub

    }

}
