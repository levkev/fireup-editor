package ch.levkev.omeganote.view;

import java.io.File;

import ch.levkev.omeganote.MainApp;
import ch.levkev.omeganote.modelling.FolderBrowser;
import ch.levkev.omeganote.modelling.Notebook;
import ch.levkev.omeganote.modelling.interfaces.IFolder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;

public class NotebookController {
    private MainApp mainApp;
    
    @FXML
    private Label currentDirectoryLabel;
    
    @FXML
    private ListView<IFolder> currentDirectoriesList;
    
    private File currentDirectory;
    
    public NotebookController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	this.updateCurrentDirectoryList();
    }
    
    /**
     * updates the current directory list and the label for that
     */
    private void updateCurrentDirectoryList() {
    	this.currentDirectoriesList.getItems().clear();
    	this.currentDirectoryLabel.setText(this.getCurrentDirectory().getPath());
    	FolderBrowser browser = new FolderBrowser(this.getCurrentDirectory());
    	this.currentDirectoriesList.getItems().addAll(browser.listFiles());
    }
    
    /**
     * open a notebook in that specific directory
     * 
     * @param path
     */
    private void openNotebook(File path) {
    	Notebook nb = new Notebook(path);
    	this.mainApp.setCurrentNotebook(nb);
    	this.mainApp.showEditorView();
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
    private void onNewButtonClicked() {
    	DirectoryChooser dirChooser = new DirectoryChooser();
    	dirChooser.setTitle("New Notebook location");
    	dirChooser.setInitialDirectory(this.getCurrentDirectory());
    	File selectedDir = dirChooser.showDialog(this.mainApp.getPrimaryStage());
    	if (selectedDir == null) return;
    	
    	this.openNotebook(selectedDir);
    }
    
    @FXML
    private void onListItemClicked() {
    	IFolder clickedFolder = this.currentDirectoriesList.getSelectionModel().getSelectedItem();
    	if (clickedFolder == null) return;
    	if (clickedFolder.isNotebook()) {
    		this.openNotebook(clickedFolder.getFile());
    	} else {
    		this.currentDirectory = clickedFolder.getFile();
    		this.updateCurrentDirectoryList();
    	}
    }
    
    @FXML
    private void onUpButtonClicked() {
    	this.currentDirectory = this.getCurrentDirectory().toPath().getParent().toFile();
    	this.updateCurrentDirectoryList();
    }
    
    private File getCurrentDirectory() {
    	if (this.currentDirectory == null) {
    		this.currentDirectory = new File(System.getProperty("user.home")); 
    	}
    	return this.currentDirectory;
    }
}
