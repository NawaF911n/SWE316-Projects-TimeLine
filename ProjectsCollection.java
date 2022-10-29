import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProjectsCollection {
	private static ProjectsCollection instance = null;
	private ArrayList<Project> projects = new ArrayList<Project>();
	
	
	private ProjectsCollection() {
	
	}
	
	public static ProjectsCollection getInstance() {
		if(instance == null) 
			instance = new ProjectsCollection();
			
		return instance;
	}
	
	public void add(Project project) {
		projects.add(project);
		
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}
	

}
