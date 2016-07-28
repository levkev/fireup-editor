package ch.levkev.omeganote.view;

import ch.levkev.omeganote.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RootController {
    private MainApp mainApp;

    @FXML
    private Button editorButton;
    
    @FXML
    private Button notebookButton;
    
    public RootController() {
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
    private void onEditorButtonClicked() {
    	this.mainApp.showEditorView();
    }
    
    @FXML
    private void onNotebookButtonClicked() {
    	this.mainApp.showNotebookView();
    }
}
