
public class RiderThread extends Thread{
	
	// Each rider needs to be in a building. They also need to start somewhere and end somewhere
	
	private Building building;
	int destination;
	int start;
	
	public RiderThread(Building building, int destination, int start) {
		this.building = building;
		this.destination = destination;
		this.start = start;
	}
	
	@Override
	public void run() {
		
	}

}
