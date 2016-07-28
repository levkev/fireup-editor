package ch.levkev.omeganote.view;

import java.io.IOException;

import ch.levkev.omeganote.MainApp;
import ch.levkev.omeganote.modelling.Notebook;
import ch.levkev.omeganote.modelling.interfaces.ISection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class EditorController {
    private MainApp mainApp;
    
    @FXML
    private TabPane tabpane;
    
    public EditorController() {
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
        
        this.loadNotebook();
    }
    
    private void loadNotebook() {
    	Notebook nb = this.mainApp.getCurrentNotebook();
    	if (nb == null) {
    		return;
    	}
    	
    	
    	// load all sections into tabs
    	//this.tabpane.getTabs().clear();
    	for (ISection section : nb.getSections()) {
    		Tab tab = new Tab();
    		tab.setText(section.getName());
    		try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/SectionView.fxml"));
                AnchorPane sectionView = (AnchorPane) loader.load();
                tab.setContent(sectionView);
                
                SectionController controller = loader.getController();
                controller.setMainApp(this.mainApp);
                controller.setSection(section);
            } catch (IOException e) {
                e.printStackTrace();
            }
    		
    		
    		this.tabpane.getTabs().add(tab);
    	}
    }
}
