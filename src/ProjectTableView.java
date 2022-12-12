
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProjectTableView extends TableView<Project> {
	private ProjectsCollection projectsCollection;
	private Project selectedProject;


	public ProjectTableView(ProjectsCollection projectsCollection) {
		this.projectsCollection = projectsCollection;
		setTableView();
	}

	private void setTableView() {
		// Creating and setting column for project id
		TableColumn<Project, String> IDcolumn = new TableColumn<>("Project ID");
		IDcolumn.setCellValueFactory(new PropertyValueFactory<>("projectID"));
		IDcolumn.setPrefWidth(140);
	
		// Creating and setting column for stage number
		TableColumn<Project, Integer> StageColumn = new TableColumn<>("Stage Number");
		StageColumn.setCellValueFactory(new PropertyValueFactory<>("stageNum"));
		StageColumn.setPrefWidth(80);
		
		// Add the columns to table view
		super.getColumns().add(IDcolumn);
		super.getColumns().add(StageColumn);
		super.setPrefHeight(600);
	
		// Adding all the projects to the table view
		for (Project project : projectsCollection.getAll())
			super.getItems().add(project);		
	}

	public Project searchProject(String Id) {

		getItems().stream()
	    .filter(item -> item.getProjectID().equals(Id))
	    .findAny()
	    .ifPresent(item -> {
	    	getSelectionModel().select(item);
	    	scrollTo(item);
	    	selectedProject = item;
	    });
		return selectedProject;
	    }
}