import java.time.LocalDate;
import java.util.ArrayList;

public class StagesCollection {
	private ArrayList<Stagee> stagees = new ArrayList<Stagee>();
	
	public StagesCollection() {
		
	}
	public void add(Stagee stagee) {
		stagees.add(stagee);
	}
	
	public ArrayList<Stagee> getStages(){
		return stagees; 
	}
	

	

}
