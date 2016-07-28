package ch.levkev.omeganote.view;

import ch.levkev.omeganote.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;

public class EditorController {
    private MainApp mainApp;

    @FXML
    private TextArea textarea;
    
    @FXML
    private WebView output;
    
    @FXML
    private ListView sectionList;
    
    public EditorController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void onTextChanged() {
    	
    }
    
    @FXML
    private void onProcessButtonClicked() {
    	
    }
}
