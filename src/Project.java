
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


public class Project {
    private String projectID;
    private int stageNum;
    private int duration;
    private StagesCollection stagesCollection = new StagesCollection();

    public Project(String projectID,int stageNum) {
        this.projectID = projectID;
        this.stageNum = stageNum;
    }

    public String getProjectID(){
        return projectID;
    }

    public int getDuration(){
        if (stagesCollection.isEmpty())
            duration = 0;

        LocalDate firstStage = stagesCollection.get(0).getDate();
        LocalDate lastStage = stagesCollection.get(stagesCollection.size() - 1).getDate();
        long diff = Duration.between(firstStage.atStartOfDay(),lastStage.atStartOfDay()).toDays();
        
        duration = diff == 0? 1 : (int)diff;
        return duration;
    }
    public int getStageNum(){
        return stageNum;
    }

    public StagesCollection getStagesCollection() {
        return stagesCollection;
    }
    
    public int getDurationMoanth() {
    	StageData first = stagesCollection.get(0);
    	StageData last = stagesCollection.get(stagesCollection.size() - 1);

        return (last.getDate().getMonthValue() + (12 * (last.getDate().getYear()
        		- first.getDate().getYear()))) - first.getDate().getMonthValue() + 1;


    }

    public int[] getReworks() {
    	int awarded = 0;
    	boolean before = true;
    	int afterAwarded = 0;
    	int beforeAwarded = 0;
    	int preNewValue = 0;
    	
    	for(int i = 0; stagesCollection.size() > i; ++i) {
    		int newValue = stagesCollection.getAll()[i].getNewValue(); 
    			
    		if(newValue < preNewValue) {
    			if(before) {
    				++beforeAwarded;
    				if(awarded == 0)
        				awarded = preNewValue + 1;
    			}else if(!before && awarded >= newValue) {
    				++afterAwarded;
    				
    			}
    		}
    		if(awarded == newValue)
    			before = false;    		
    		preNewValue = newValue;
    	
    	}

		int[] arr = new int[2];
		arr[0] = beforeAwarded;
		arr[1] = afterAwarded;
		
		return arr;
    }
}
