import java.time.LocalDate;
import java.time.LocalDateTime;

public class Project {
	private String projectID;
	private Integer stageNumber;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDateTime createdOn;
	private LocalDateTime changeOn;
	private int customerID;
	private String currency;
	private StagesCollection stages = new StagesCollection();
	
	public Project(
			String projectID,
			Integer stageNumber,
			LocalDate startDate,
			LocalDate endDate,
			LocalDateTime createdOn,
			LocalDateTime changeOn,
			int customerID,
			String currency){
		
		this.projectID = projectID;
		this.stageNumber = stageNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.changeOn = createdOn;
		this.changeOn = changeOn;
		this.customerID = customerID;
		this.currency = currency;
				
	}

	public String getProjectID() {
		return projectID;
	}

	public Integer getStageNumber() {
		return stageNumber;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public LocalDateTime getChangeOn() {
		return changeOn;
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getCurrency() {
		return currency;
	}

	public StagesCollection getStageCollection() {
		return stages;
	}
	public int getDuration() {
		return 78;
	}

	
	

}
