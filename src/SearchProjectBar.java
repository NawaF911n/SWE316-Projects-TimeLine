
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchProjectBar extends Label {

	private TextField searchProject;
	
	public SearchProjectBar(TextField searchProject) {
		super("Project ID:", searchProject);
		this.searchProject = searchProject;
		setSearchProjectBar();
	}
	
	private void setSearchProjectBar(){
		// Setting a text field, so we can search for a certen project
		searchProject.setPrefSize(100, 20);

		super.setContentDisplay(ContentDisplay.RIGHT);
		super.setPadding(new Insets(10, 10, 10, 10));
	}

}
