package cr.ac.una.tarea.controller;

import java.net.URL;
import java.util.ResourceBundle;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AdminUsersController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private StackPane root;

    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXButton btnNew;

    @FXML
    private MFXButton btnSave;

    @FXML
    private ImageView imgViewUser;

    @FXML
    private TableView<?> tbvUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
         @Override
    public void initialize() {
    }



    @FXML
    void onActionBtnDelete(ActionEvent event) {

    }

    @FXML
    void onActionBtnNew(ActionEvent event) {

    }

    @FXML
    void onActionBtnSave(ActionEvent event) {

    }

}

