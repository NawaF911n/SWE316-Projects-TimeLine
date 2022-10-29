import java.time.LocalDate;

public class StageData {
	
	private int docNumber;
    private int newValue;
    private int oldValue;
    private LocalDate date;
    private boolean status;

    public StageData(int docNumber, int newValue, int oldValue, LocalDate date) {
        this.docNumber = docNumber;
        this.newValue = newValue;
        this.oldValue = oldValue;
        this.date = date;
    }

    //To find if it green or red flag
    public boolean getStatus (){
        status = (newValue - oldValue) >= 0;
        return status;
    }

    public int getDocNumber() {
        return docNumber;
    }

    public int getNewValue() {
        return newValue;
    }

    public LocalDate getDate() {
        return date;
    }


}
