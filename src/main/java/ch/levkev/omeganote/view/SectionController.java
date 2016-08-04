package ch.levkev.omeganote.view;

import java.io.IOException;

import ch.levkev.omeganote.MainApp;
import ch.levkev.omeganote.modelling.interfaces.ISection;
import ch.levkev.omeganote.modelling.interfaces.ITitle;
import ch.levkev.omeganote.modelling.SectionParser;
import ch.levkev.omeganote.modelling.TitleNode;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SectionController {
    public static final String OUTPUT_CSS_LOCATION = "css/output.css";
	public static final String TEXTAREA_CSS_LOCATION = "css/textarea.css";
    
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
        this.applyCss();
        
    }
    
    public void setSection(ISection section) {
    	assert (section != null);
    	this.section = section;
    	
    	this.loadContent();
    	this.loadTitles();
    	this.updateParsedContent();
    }
    
    private void applyCss() {
    	WebEngine engine = output.getEngine();
    	engine.setUserStyleSheetLocation(getClass().getResource(OUTPUT_CSS_LOCATION).toString());

        Scene scene = mainApp.getPrimaryStage().getScene();
        scene.getStylesheets().add(getClass().getResource(TEXTAREA_CSS_LOCATION).toString());
    	
    }
    
    private void updateParsedContent() {
    	SectionParser parser = new SectionParser();
    	String parsedContent = parser.sectionToHtml(section);
    	WebEngine engine = output.getEngine();
    	engine.loadContent(parsedContent);
	}

	private void loadTitles() {
		this.titlesList.getItems().clear();
		for (TitleNode titleNode : this.section.getTitleNodes()) {
			this.titlesList.getItems().add(titleNode.getTitle());
			if (!titleNode.isCollapsed())
				loadDescendants(titleNode);
		}
	}
	
	private void loadDescendants(TitleNode titleNode) {
		for (TitleNode child : titleNode.getChildren()) {
			this.titlesList.getItems().add(child.getTitle());
			if (!child.isCollapsed()) {	
				loadDescendants(child);
			}
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
    	this.updateParsedContent();
    }
}
