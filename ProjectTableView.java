
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ProjectTableView {
	private ProjectsCollection projectsCollection;
	private BorderPane borderPane;

	public ProjectTableView(ProjectsCollection projectsCollection,BorderPane borderPane) {
		this.projectsCollection = projectsCollection;
		this.borderPane = borderPane;
	}

	public VBox getProjectTableView() {
		VBox Vbox = new VBox(10);
		TableView<Project> tableView = setTableView();
		Vbox.getChildren().addAll(searchProjectField(tableView), tableView);
		Vbox.setSpacing(10);
		Vbox.setPadding(new Insets(20, 0, 20, 20));
		return Vbox;
	}
	private TableView<Project> setTableView() {
		// Creating and setting column for project id
		TableColumn<Project, String> IDcolumn = new TableColumn<>("Project ID");
		IDcolumn.setCellValueFactory(new PropertyValueFactory<>("projectID"));
		IDcolumn.setPrefWidth(140);
	
		// Creating and setting column for stage number
		TableColumn<Project, Integer> StageColumn = new TableColumn<>("stageNumber");
		StageColumn.setCellValueFactory(new PropertyValueFactory<>("stageNumber"));
		StageColumn.setPrefWidth(80);
		
		// Creating table view and add the columns
		TableView<Project> tableView = new TableView<Project>();
		tableView.getColumns().add(IDcolumn);
		tableView.getColumns().add(StageColumn);
		tableView.setPrefHeight(600);
	
		// Adding all the projects to the table view
		for (Project project : projectsCollection.getProjects())
			tableView.getItems().add(project);
		
		return tableView;
				
	}

	private Label searchProjectField(TableView<Project> tableView) {
		// Creating and setting a text field, so we can search for a certen project
		TextField searchProject = new TextField();
		searchProject.setPrefSize(100, 20);
		Label searchProjectLable = new Label("Project ID:", searchProject);
		searchProjectLable.setContentDisplay(ContentDisplay.RIGHT);
		searchProjectLable.setPadding(new Insets(10, 10, 10, 10));

		// To handle the selected project by the text field 
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	tableView.getItems().stream()
        	    .filter(item -> item.getProjectID().equals(searchProject.getText()))
        	    .findAny()
        	    .ifPresent(item -> {
        	    	tableView.getSelectionModel().select(item);
        	    	tableView.scrollTo(item);
        	    	// Creating time line to draw in the screen
        	    	TimeLine timeLine = new TimeLine(item);
        	    	Group root = timeLine.getTimeLine();
        			borderPane.setCenter(root);
        	    });
            }
        };
        
        // If the user typed something it will activate this event
        searchProject.setOnAction(event);
        
        return searchProjectLable;
		
	}
}