import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TimeLine {
	Dictionary<String, Object> dictDays = new Hashtable<String, Object>();
	Project selectedProject;
	
	public TimeLine(Project selectedProject) {
		this.selectedProject = selectedProject;
	}
	
	public Group getTimeLine() {
		Group root = new Group();
		if(selectedProject != null) {
			drawTimeLine(root);
			drawFlags(root);
			drawDuration(root);
			return root;
		}
		return root;
	}

	private void drawTimeLine(Group root) {
		// List of short Format Months Names
		List<String> shortFormatMonthsNames =  Arrays.asList((new DateFormatSymbols()).getShortMonths());
				
		// To place the base line
		Line line = new Line();
		line.setStartX(0); 
		line.setStartY(300);  
		line.setEndX(1000);
		line.setEndY(300);
		root.getChildren().add(line);
		
		
		// To get the month from The first stage in the project
		StageData stage = selectedProject.getStagesCollection().get(0);
		LocalDate dateStage = stage.getDate();
		
		// Set the the date that is first of the month to display it in the time line
		int monthValue = dateStage.getMonthValue() - 1;
		int yearValue = dateStage.getYear();
		LocalDate currentDate = LocalDate.of(yearValue, monthValue + 1, 1);
		
		// Number of months that will be displayed 
		int timeLineMonths = selectedProject.getDurationMoanth() + 1;
		System.out.println(timeLineMonths);

		// Space between each month in the time line
		double spaceBtwMonths = 1000/(timeLineMonths-1);

		
		double positionXM  = 0;
		double positionXD = 0;
		String monthName = ""; 
		
		// To display the time line without flags
		for(int i = 0; i < timeLineMonths ;++i) {
			
			// if the month is 12, start over again from 1 and add a year
			if(monthValue == 12) {
				monthValue = 0;
				++yearValue;
				}
			
			// Get the short Format of month name, and display it
			monthName = shortFormatMonthsNames.get(monthValue);
			root.getChildren().add(monthBar(monthName +" "+ yearValue, positionXM ));
		
			// Go the next month and check if it is the end
			++monthValue;
			if(i != timeLineMonths -1) {
				
				
				// Check how much days in the month
				int monthDays = 31;
				if(monthValue == 2)
					monthDays = 28;
				else if(monthValue == 4 || monthValue == 6 || monthValue == 9 ||monthValue == 11)
					monthDays = 30;
				
			// The space between each day in the time line
			double spaceBtwDays = spaceBtwMonths/monthDays;
			
			// To display the days in the time line
			for(int j = 0; j < monthDays ;++j) {
				// Draw a day in the line
				root.getChildren().add(new Rectangle(positionXD,297,1,3));
				
				// Store the date and its position X 
				dictDays.put(currentDate.toString(), positionXD); 
				positionXD += spaceBtwDays;
				
				// Add one day to the date of the time line
				currentDate = currentDate.plusDays(1);
			}
		}
			
			// The position X of the next month
			positionXM += spaceBtwMonths;
			
			// The position X of the next day after the new month
			positionXD = positionXM ;
		}
	}

	private void drawFlags(Group root) {
		StagesCollection stages = selectedProject.getStagesCollection();

		// Imaginary date, so I can compare it with the stage date 
		LocalDate preDate = LocalDate.of(4000, 1, 1);
		LocalDate currentDate;
		
		// To draw the flag for each stage
		for(int i = 0; i < stages.size();++i) {
			
			// Getting some info from the stage
			StageData stage = stages.get(i);
			currentDate = stage.getDate();
			int newValue = stage.getNewValue();
			
			// Getting X position of the flag from the dic time line 
			double positionX = (double) dictDays.get(currentDate.toString());
			
			// Creating the flag and draw them
			Rectangle flagpole = new Rectangle(positionX,285 - 15 * newValue,1, 15 + 15 * newValue);
			Rectangle box =  new Rectangle(positionX,285 - 15 * newValue,10 + newValue * 2 ,10);
			Text flagValue = new Text(newValue+"");
			flagValue.setX(positionX + 18);
			flagValue.setY(285 - 15 * newValue);
			
			Text textDate = new Text(currentDate.toString());
			textDate.setStyle("-fx-font: 9 arial;");
			textDate.setX(positionX - 25);
			textDate.setY(330);
			
			
			// Setting the color of the flag based on the new value and old value
			if(stage.getStatus()) {
				box.setFill(javafx.scene.paint.Color.GREEN);
				flagValue.setFill(Color.GREEN);
				textDate.setFill(Color.GREEN);}
			else {
				box.setFill(javafx.scene.paint.Color.RED);
				flagValue.setFill(Color.RED);
				textDate.setFill(Color.RED);}
			
			flagpole.setFill(javafx.scene.paint.Color.ORANGE);
			
			root.getChildren().addAll(flagpole, box, flagValue);
			
			// if the date of the flags is not duplicated, display the date
			if(!preDate.equals(currentDate)) {
				root.getChildren().add(textDate);
				preDate = currentDate;
			}
		
		}
	}
	
	private VBox monthBar(String month, double positionX) {
		// Creating the month to dispaly it in the time line
		VBox monthBar = new VBox();
		Rectangle point = new Rectangle(0,295,2,10);
		Text dateText = new Text(month);
		dateText.setStyle("-fx-font: 11 arial;");

		monthBar.getChildren().addAll(point,dateText);
		monthBar.setLayoutX(positionX);
		monthBar.setLayoutY(295);

		return monthBar;
	}
	
	private void drawDuration(Group root) {
		
		StagesCollection stages = selectedProject.getStagesCollection();
		int duration = selectedProject.getDuration(); //  replace it with stages.getDuration
		
		// Getting the X positions of the first and last date stage from dic that store the day with its position in the time line
		double startPositionx = (double) dictDays.get(stages.get(0).getDate().toString());
		double endPositionx = (double) dictDays.get(stages.get(stages.size()-1).getDate().toString());
		
		// Setting and Drawing the Duration Line
		Line durationLine = new Line();
		durationLine.setStartX(startPositionx); 
		durationLine.setStartY(100);  
		durationLine.setEndX(endPositionx);
		durationLine.setEndY(100);
		
		Rectangle startLine = new Rectangle(startPositionx,95,1,10);
		Rectangle endLine = new Rectangle(endPositionx,95,1,10);
		
		durationLine.setStroke(javafx.scene.paint.Color.RED);
		startLine.setFill(javafx.scene.paint.Color.RED);
		endLine.setFill(javafx.scene.paint.Color.RED);
		
		// Setting and Drawing text duration above the duration line
		Text durationText = new Text("Duration: " + duration + " days");
		durationText.setFill(Color.RED);
		// Center the text
		durationText.setX((endPositionx - startPositionx) / 2 + startPositionx - 50);
		durationText.setY(90);
		
		
		root.getChildren().addAll(durationLine, startLine, endLine, durationText);
		

		
	}
}
	
