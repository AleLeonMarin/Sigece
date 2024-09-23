package cr.ac.una.mailapp.controller;

import cr.ac.una.mailapp.util.AppContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MaxViewHTMLController extends Controller implements Initializable {

    @FXML
    private WebView webHTML;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void initialize() {
        String htmlContent = (String) AppContext.getInstance().get("htmlContent");


        if (htmlContent != null && !htmlContent.isEmpty()) {
            webHTML.getEngine().loadContent(htmlContent);
        }
    }
}
