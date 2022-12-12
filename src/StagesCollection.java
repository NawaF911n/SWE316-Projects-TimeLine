import java.util.ArrayList;
import java.util.Collections;


public class StagesCollection implements BasicCollection<StageData>{

    private ArrayList<StageData> stages;

    public StagesCollection(){
        stages = new ArrayList<>();
    }

    public void add(StageData stage){
    	if(stages.size() != 0){
    		StageData lastStage = stages.get(stages.size() - 1);
    		
    		if(stage.getDate().isEqual(lastStage.getDate())) {
    			if(stages.size() >= 2) {
    				StageData beforeLastStage = stages.get(stages.size() - 2);
    				
    				if(beforeLastStage.getNewValue() == stage.getOldValue()) {
    					stages.add(stages.size() - 1, stage);
    					return;
    				}
    			}
    			else {
    				if(stage.getOldValue() == 0) {
    					stages.add(stages.size() - 1, stage);
    					return;
    				}
    			}
    		}
    	}
    	
    	stages.add(stage);


    }

    public StageData[] getAll(){
    	StageData[] arr = new StageData[stages.size()];

        for (int i = 0; i < stages.size(); i++){
            arr[i] = stages.get(i);
        }
        
        return arr;
    }

    @Override
    public int size() {
        return stages.size();
    }

    @Override
    public boolean isEmpty() {
        return stages.isEmpty();
    }

    @Override
    public StageData get(int i) {
        return stages.get(i);
    }
    
    
}

