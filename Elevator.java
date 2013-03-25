public class Elevator extends AbstractElevator implements Runnable {

	protected Building b;

	protected int elevatorId;
	protected int numFloors;
	protected int maxOccupancyThreshold;

	protected int currentFloor = 1;
	protected int currentOccupancy = 0;
	protected int destination = 1;
	protected boolean currentDirectionUp = true;

	protected int[] requestsperfloor;
	protected int[] currentriderdestinations;

	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		super(numFloors, elevatorId, maxOccupancyThreshold);
		this.numFloors = numFloors;
		this.elevatorId = elevatorId;
		this.maxOccupancyThreshold = maxOccupancyThreshold;
		this.requestsperfloor = new int[numFloors+1];
	}

	@Override
	public void run() {
		VisitFloor(destination);
	}

	// Let people out
	public synchronized void OpenDoors() {
		while(currentriderdestinations[currentFloor]!=0) {
			Exit();
		}
	}

	// Let people in until maximum capacity is reached
	public synchronized void ClosedDoors() {
		int startingnumber = requestsperfloor[currentFloor];
		while(Enter() || requestsperfloor[currentFloor] == 0) {
			currentOccupancy++;
			requestsperfloor[currentFloor]--;
		}
		System.out.println("Picked up " + (startingnumber - requestsperfloor[currentFloor]) + " people");
	}

	// Go to a particular floor to drop off or pick up
	public synchronized void VisitFloor(int floor) {
		while(floor!=currentFloor) {
			// If there is a request or drop-offs that can be satisfied on the way to the destination, do so
			if(requestsperfloor[currentFloor]!=0 || currentriderdestinations[currentFloor]!=0) {
				OpenDoors();
				ClosedDoors();
			}
			if(floor>currentFloor) {
				currentFloor++;
			}
			else{
				currentFloor--;
			}
		}
		
		// Satisfy any requests or drop-offs at the floor to visit
		if(requestsperfloor[currentFloor]!=0 || currentriderdestinations[currentFloor]!=0) {
			OpenDoors();
			ClosedDoors();
		}
		
		if(floor == destination && currentDirectionUp) {
			for(int i = 1; i==numFloors; i++) {
				if(currentriderdestinations[i]!=0) {
					destination = i;
					break;
				}
			}
		}
		else if(floor == destination && !currentDirectionUp) {
			for(int i = numFloors; i==1; i--) {
				if(currentriderdestinations[i]!=0) {
					destination = i;
					break;
				}
			}
		}
		
		if(currentFloor!=destination) {
			VisitFloor(destination);
		}
	}
	
	// Set Destination and add request. Currently method is to go up to the highest and then down to the lowest.
	public synchronized void requestFloor(int floor) {
		requestsperfloor[floor]++;
		if(destination<floor && currentDirectionUp) {
			destination = floor;
		}
		else if(destination>floor && !currentDirectionUp){
			destination = floor;
		}
	}

	// Can people Enter?
	public synchronized boolean Enter() {
		return currentOccupancy < maxOccupancyThreshold;
	}

	public synchronized void Exit() {
		currentOccupancy--;
		currentriderdestinations[currentFloor]--;
	}

	// Floor call
	public synchronized void RequestFloor(int floor) {
		requestsperfloor[floor]++;
	}
	
	public synchronized boolean iscurrentDirectionUp() {
		return currentDirectionUp;
	}

	public synchronized int getCurrentFloor() {
		return this.currentFloor;
	}
	
	public synchronized boolean isIdle() {
		OpenDoors();
		ClosedDoors();
		return this.destination == currentFloor;
	}
}