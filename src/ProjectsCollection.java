import java.util.ArrayList;

public class ProjectsCollection implements BasicCollection<Project> {

    private static ProjectsCollection instance = new ProjectsCollection();
    private ArrayList<Project> projects = new ArrayList<Project>();

    private ProjectsCollection(){

    }


    public void add(Project project){
        projects.add(project);
    }

    @Override
    public int size() {
        return projects.size();
    }

    @Override
    public boolean isEmpty() {
        return projects.isEmpty();
    }

    @Override
    public Project get(int i) {
        return projects.get(i);
    }

    public Project[] getAll(){
       Project[] arr = new Project[projects.size()];

       for (int i = 0; i < projects.size(); i++){
           arr[i] = projects.get(i);
       }

       return arr;
    }

    public static ProjectsCollection getInstance(){
        return instance;
    }


}