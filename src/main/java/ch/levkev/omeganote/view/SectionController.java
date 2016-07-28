package ch.levkev.omeganote.view;

import ch.levkev.omeganote.MainApp;
import ch.levkev.omeganote.modelling.Notebook;
import ch.levkev.omeganote.modelling.interfaces.ISection;
import ch.levkev.omeganote.modelling.interfaces.ITitle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;

public class SectionController {
    private MainApp mainApp;

    @FXML
    private TextArea textarea;
    
    @FXML
    private WebView output;
    
    @FXML
    private ListView titlesList;
    
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
		for (ITitle title : this.section.getTitles()) {
			this.titlesList.getItems().add(title.getTitle());
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
    	
    }
}
