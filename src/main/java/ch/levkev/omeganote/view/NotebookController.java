package ch.levkev.omeganote.view;

import java.io.File;

import ch.levkev.omeganote.MainApp;
import ch.levkev.omeganote.modelling.Notebook;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

public class NotebookController {
    private MainApp mainApp;
    
    public NotebookController() {
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
    private void onOpenButtonClicked() {
    	DirectoryChooser dirChooser = new DirectoryChooser();
    	dirChooser.setTitle("Open Resource File");
    	File selectedDir = dirChooser.showDialog(this.mainApp.getPrimaryStage());
    	if (selectedDir == null) return;
    	
    	Notebook nb = new Notebook(selectedDir.getPath());
    	this.mainApp.setCurrentNotebook(nb);
    	this.mainApp.showEditorView();
    }
}
