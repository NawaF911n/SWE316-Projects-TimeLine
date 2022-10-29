
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
	public ProjectsCollection projectsCollection;
	
	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		projectsCollection = ProjectsCollection.getInstance();

		// To test the code
		Project project = new Project("S-0053",4,null,null,null,null,2,"sar");
		project.getStageCollection().add(new Stagee(555275,1,0,LocalDate.of(2021, 11, 17),LocalTime.now()));
		project.getStageCollection().add(new Stagee(555281,2,1,LocalDate.of(2021, 11, 17),LocalTime.now()));
		project.getStageCollection().add(new Stagee(577509,3,2,LocalDate.of(2022, 1, 20),LocalTime.now()));
		project.getStageCollection().add(new Stagee(579998,2,3,LocalDate.of(2022, 1, 26),LocalTime.now()));
		project.getStageCollection().add(new Stagee(584655,3,2,LocalDate.of(2022, 2, 3),LocalTime.now()));
		project.getStageCollection().add(new Stagee(584656,4,3,LocalDate.of(2022, 2, 3),LocalTime.now()));
		projectsCollection.add(project);

		// Set the title and screen, then display the screen
		arg0.setTitle("Project Timeline");
		arg0.setScene(screen());
		arg0.show();
	}
	
	public Scene screen() {
		BorderPane borderPane = new BorderPane();
		ProjectTableView projectTableView = new ProjectTableView(projectsCollection, borderPane);
		borderPane.setLeft(projectTableView.getProjectTableView());
		
		Scene scene = new Scene(borderPane, 1500, 720);
		return scene;
	}

		
}
