package ch.levkev.omeganote;

import java.io.IOException;

import ch.levkev.omeganote.modelling.Notebook;
import ch.levkev.omeganote.view.EditorController;
import ch.levkev.omeganote.view.NotebookController;
import ch.levkev.omeganote.view.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The MainApp, which starts the application and loads the RootLayout Controller
 * 
 * @author kreemer
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Notebook currentNotebook;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MainApp");
		
		initRootLayout();
		
		showEditorView();
	}
	
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            RootController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showEditorView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditorView.fxml"));
            AnchorPane editorView = (AnchorPane) loader.load();

            rootLayout.setCenter(editorView);
            
            EditorController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNotebookView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/NotebookView.fxml"));
            AnchorPane notebookView = (AnchorPane) loader.load();

            rootLayout.setCenter(notebookView);
            
            NotebookController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setCurrentNotebook(Notebook nb) {
    	assert(nb != null);
    	this.currentNotebook = nb;
    }
    
    public Notebook getCurrentNotebook() {
    	return this.currentNotebook;
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
	public static void main(String[] args) {
		launch(args);
	}
	
}
