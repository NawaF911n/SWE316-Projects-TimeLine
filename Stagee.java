import java.time.LocalDate;
import java.time.LocalTime;

public class Stagee {
	private int decNumber;
	private int newValue;
	private int oldValue;
	private LocalDate date;
	private LocalTime time;
	
	public Stagee(int decNumber,
			int newValue,
			int oldValue,
			LocalDate date,
			LocalTime time) {
		
		this.decNumber = decNumber;
		this.newValue = newValue;
		this.oldValue = oldValue;
		this.date = date;
		this.time = time;
	}

	public int getDecNumber() {
		return decNumber;
	}

	public int getNewValue() {
		return newValue;
	}

	public int getOldValue() {
		return oldValue;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}
	
	


}
