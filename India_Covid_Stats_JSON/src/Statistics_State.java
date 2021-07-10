import java.time.LocalDateTime;

public class Statistics_State {

	private String name;
	private int confirmed;
	private int active;
	private int recovered;
	private int deceased;
	private LocalDateTime lastUpdated;
	
	
	public void displayData()
	{
	    String name;
		int active, confirmed, recovered, deceased;
		name = this.name;
    	confirmed = this.confirmed;
    	active = this.active;
    	recovered = this.recovered;
    	deceased = this.deceased;
    	System.out.format("%50s %10d %10d %10d %10d",name,confirmed,active,recovered,deceased);
    	System.out.println("");
	}
	
	@Override
	public String toString() {
		return name + "\t\t\t\t" + confirmed + "\t" + active + "\t" + recovered + "\t" + deceased ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getRecovered() {
		return recovered;
	}
	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}
	public int getDeceased() {
		return deceased;
	}
	public void setDeceased(int deceased) {
		this.deceased = deceased;
	}
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
