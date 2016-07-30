package ch.levkev.omeganote.view;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import ch.levkev.omeganote.MainApp;
import ch.levkev.omeganote.modelling.Notebook;
import ch.levkev.omeganote.modelling.Section;
import ch.levkev.omeganote.modelling.interfaces.ISection;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

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
    
    private void loadTabs() {
    	Notebook nb = this.mainApp.getCurrentNotebook();
    	if (nb == null) {
    		return;
    	}
    	
    	// load all sections into tabs
    	this.tabpane.getTabs().clear();
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
    	
    	// Add an "add" tab
    	Tab tab = new Tab();
    	tab.setText("+");
    	this.tabpane.getTabs().add(tab);
    	tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (tab.isSelected()) {
                	onNewTabClicked();
                }
            }
        });
    	
    }
    
    private void loadNotebook() {
    	this.loadTabs();
    	if (noTabs()) {
    		onNewTabClicked();
    	}
    	
    }
    
    private boolean noTabs() {
    	return this.tabpane.getTabs().size() == 1;
	}

	@FXML
    private void onNewTabClicked() {
    	TextInputDialog dialog = new TextInputDialog("new section");
    	dialog.setTitle("Enter name for section");
    	dialog.setContentText("Please enter the name of your new section:");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    this.createNewSection(result.get());
    	}
    	else {
    		if (noTabs()) {
    			onNewTabClicked();
    		}
    	}
    }
    
    private void createNewSection(String name) {
		File sectionFile = new File(
			this.mainApp.getCurrentNotebook().getPath() + "/" + 
			Notebook.DIR_CONTAINING_SECTIONS + "/" 
			+ name + Section.SECTION_SUFFIX
		);
		try {
			sectionFile.createNewFile();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setContentText("Ooops, there was an error while creating the section");

    		alert.showAndWait();
		}
    	Section section = new Section(sectionFile);
    	this.mainApp.getCurrentNotebook().getSections().add(section);
    	
    	this.loadTabs();
    	
    	Tab searchedTab = this.tabpane.getTabs().get(0);
    	for (Tab tab : this.tabpane.getTabs()) {
    		if (tab.getText().toString().equals(name)) {
    			searchedTab = tab;
    		}
    	}
    	
    	SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
    	selectionModel.select(searchedTab);
    }
}
