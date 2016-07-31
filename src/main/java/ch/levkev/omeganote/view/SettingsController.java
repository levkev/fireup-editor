package ch.levkev.omeganote.view;

import java.io.File;

import ch.levkev.omeganote.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class SettingsController {
    private MainApp mainApp;
    
    @FXML
    private TextField homeDirTextField;
    
    public SettingsController() {
    }

    /**
     * This method is fired when the editor pane is called or displayed
     * It'll load the current notebook into the view
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
        this.homeDirTextField.setText(mainApp.getSettings().getHomeDirectory().getPath());
    }
    
    @FXML
    private void onSelectHomeDirectoryButtonClicked() {
    	DirectoryChooser dirChooser = new DirectoryChooser();
    	dirChooser.setTitle("New Notebook location");
    	dirChooser.setInitialDirectory(this.mainApp.getSettings().getHomeDirectory());
    	File selectedDir = dirChooser.showDialog(this.mainApp.getPrimaryStage());
    	if (selectedDir == null) return;
    	
    	this.homeDirTextField.setText(selectedDir.getPath());
    }
    
    @FXML
    private void onSaveButtonClicked() {
    	this.mainApp.getSettings().setHomeDirectory(this.homeDirTextField.getText());
    }
}
