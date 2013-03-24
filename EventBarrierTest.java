public class EventBarrierTest implements Runnable {

	int numWorkers = 10;
	
	public void run() {
		EventBarrier e = new EventBarrier(numWorkers);
		
	}
}
