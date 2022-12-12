
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	public ProjectsCollection projectsCollection;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		projectsCollection = ProjectsCollection.getInstance();
		ExcelReader.read();

		// Set the title and screen, then display the screen
		arg0.setTitle("Project Timeline");
		arg0.setScene(screen());
		arg0.show();
	}
	
	public Scene screen() {
		BorderPane borderPane = new BorderPane();
		
		ProjectTableView projectTableView = new ProjectTableView(projectsCollection);
		TextField searchProject = new TextField();
		SearchProjectBar searchProjectBar = new SearchProjectBar(searchProject);
		
		drawProjectContent(borderPane, projectTableView, searchProject);
		
		VBox Vbox = new VBox(10);
		Vbox.setSpacing(10);
		Vbox.setPadding(new Insets(20, 0, 20, 20));
		Vbox.getChildren().addAll(searchProjectBar, projectTableView);

		borderPane.setLeft(Vbox);

		Scene scene = new Scene(borderPane, 1500, 720);
		return scene;
	}
	
	public void drawProjectContent(BorderPane borderPane, ProjectTableView projectTableView, TextField projectTextField) {
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	Project selectedProject = projectTableView.searchProject(projectTextField.getText());
            	
        	    TimeLine timeLine = new TimeLine(selectedProject);
        	    VBox vbox = new VBox(100);
        	    vbox.getChildren().addAll(new ReworksFX(selectedProject), timeLine);
        	    vbox.setAlignment(Pos.CENTER);
        		borderPane.setCenter(vbox);
        	    };
            };
          
            // If the user typed something it will activate this event
            projectTextField.setOnAction(event);
	}

		
}
