import java.util.ArrayList;

public class StagesCollection implements BasicCollection<StageData>{

    private ArrayList<StageData> stages;

    public StagesCollection(){
        stages = new ArrayList<>();
    }

    public void add(StageData stage){
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