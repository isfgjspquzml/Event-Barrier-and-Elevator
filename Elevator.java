public class Elevator extends AbstractElevator {

	protected Building b;
	
	protected int elevatorId;
	protected int numFloors;
	protected int maxOccupancyThreshold;
	
	protected int currentFloor = 1;
	protected int currentOccupancy = 0;
	protected boolean currentDirectionUp = true;
	
	protected int[] requestsperfloors;
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		this.numFloors = numFloors;
		this.elevatorId = elevatorId;
		this.maxOccupancyThreshold = maxOccupancyThreshold;
		this.requestsperfloors = new int[numFloors];
	}

	public synchronized void OpenDoors() {
		// TODO Auto-generated method stub
		
	}

	public synchronized void ClosedDoors() {
		// TODO Auto-generated method stub
		
	}

	public synchronized void VisitFloor(int floor) {
		// TODO Auto-generated method stub
		
	}

	public synchronized boolean Enter() {
		// TODO Auto-generated method stub
		return false;
	}

	public synchronized void Exit() {
		// TODO Auto-generated method stub
		
	}

	public synchronized void RequestFloor(int floor) {
		// TODO Auto-generated method stub
		
	}
	
	public synchronized int getCurrentFloor() {
		return this.currentFloor;
	}
	
	public synchronized boolean isFull() {
		return currentOccupancy >= maxOccupancyThreshold;
	}

}
