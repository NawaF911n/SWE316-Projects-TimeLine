import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ReworksFX extends BorderPane {
	Project project;
	
	public ReworksFX(Project project) {
		this.project = project;
		if(project != null) {
			reworks();
		}
		
		
	}
	
	private void reworks() {
		int[] arr = project.getReworks();
		
		VBox vboxBefore = new VBox(10);
		Text beforeInt = new Text(arr[0] +"");
		Text before = new Text("Befor Award");
		vboxBefore.getChildren().addAll(beforeInt, before);
		vboxBefore.setAlignment(Pos.CENTER);
		
		VBox vboxAfter = new VBox(10);
		Text afterInt = new Text(arr[1] +"");
		Text after = new Text("After Award");
		vboxAfter.getChildren().addAll(afterInt, after);
		vboxAfter.setAlignment(Pos.CENTER);

		HBox Hbox = new HBox(50);
		Hbox.setAlignment(Pos.CENTER);
		Hbox.getChildren().addAll(vboxBefore, vboxAfter);
		
		VBox vboxReworks = new VBox(10);
		Text reworks = new Text("Reworks");
		vboxReworks.getChildren().addAll(reworks, Hbox);
		vboxReworks.setAlignment(Pos.CENTER);

		super.setCenter(vboxReworks);
	}
}
