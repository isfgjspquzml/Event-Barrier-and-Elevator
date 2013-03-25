public class EventBarrier extends AbstractEventBarrier {

	boolean gatedown;
	int totaltasks;
	int waitercount;
	int crossingcount;

	public EventBarrier(int numWorkers) {
		super(numWorkers);
		totaltasks = numWorkers;
	}

	public synchronized void arrive() {
		System.out.println("arrive (start)");
		waitercount++;
		System.out.println("Gate is down? "+gatedown);
		while(gatedown) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		waitercount--;
		crossingcount++;
		System.out.println("arrive (end)");
	}

	public synchronized void raise() {
		System.out.println("raise (start)");
		gatedown = false;
		this.notifyAll();
		while(crossingcount>0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		gatedown = true;
		System.out.println("raise (end)");
	}

	public synchronized void complete() {
		System.out.println("complete (start)");
		crossingcount--;
		while(crossingcount>0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		System.out.println("complete (end)");
	}

	public synchronized int waiters() {
		return waitercount;
	}
}
