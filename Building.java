import java.util.ArrayList;

public class Building extends AbstractBuilding {

	int numFloors;
	int numElevators;
	int maxOccupancyThreshold;
	
	ArrayList<Elevator> ElevatorList = new ArrayList<Elevator>();
	ArrayList<Thread> TaskList = new ArrayList<Thread>();
	
	public Building(int numFloors, int numElevators, int maxOccupancyThreshold) {
		super(numFloors, numElevators);
		this.numFloors = numFloors;
		this.numElevators = numElevators;
		this.maxOccupancyThreshold = maxOccupancyThreshold;
		
		for(int i=0; i<numElevators; i++) {
			ElevatorList.add(new Elevator(numFloors, i, maxOccupancyThreshold));
			TaskList.add(new Thread(ElevatorList.get(i)));
		}
		
		for(Thread task:TaskList) {
			task.start();
		}
	}
	
	public void CallUp(int fromFloor) {
		// TODO Auto-generated method stub
		
	}

	public void CallDown(int fromFloor) {
		// TODO Auto-generated method stub
		
	}
}
