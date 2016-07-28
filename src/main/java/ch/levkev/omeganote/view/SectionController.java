package ch.levkev.omeganote.view;

import java.io.IOException;

import ch.levkev.omeganote.MainApp;
import ch.levkev.omeganote.modelling.interfaces.ISection;
import ch.levkev.omeganote.modelling.interfaces.ITitle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;

public class SectionController {
    private MainApp mainApp;

    @FXML
    private TextArea textarea;
    
    @FXML
    private WebView output;
    
    @FXML
    private ListView<ITitle> titlesList;
    
    private ISection section;
    
    public SectionController() {
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
    }
    
    public void setSection(ISection section) {
    	assert (section != null);
    	this.section = section;
    	
    	this.loadContent();
    	this.loadTitles();
    	this.parseContent();
    }
    
    
    
    
    private void parseContent() {
		// TODO Auto-generated method stub
		
	}

	private void loadTitles() {
		this.titlesList.getItems().clear();
		for (ITitle title : this.section.getTitles()) {
			this.titlesList.getItems().add(title);
		}
	}

	private void loadContent() {
		this.textarea.setText(this.section.getContent());
		
	}

	@FXML
    private void onTextChanged() {
    	
    }
    
    @FXML
    private void onProcessButtonClicked() {

    	try {
    		this.section.save(this.textarea.getText());
    	} catch (IOException e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setContentText("Ooops, there was an error while saving the section");

    		alert.showAndWait();
    	}
    	this.loadTitles();
    }
}
