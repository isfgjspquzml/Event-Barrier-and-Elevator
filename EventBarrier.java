public class EventBarrier extends AbstractEventBarrier {

	boolean gatedown = false;
	int waitercount = 0;
	int crossingcount = 0;

	public EventBarrier(int numWorkers) {
		super(numWorkers);
	}

	public synchronized void arrive() {
		waitercount++;
		while(gatedown) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		waitercount--;
		crossingcount++;
	}

	public synchronized void raise() {
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
	}

	public synchronized void complete() {
		crossingcount--;
		while(crossingcount>0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
	}

	public synchronized int waiters() {
		return waitercount;
	}
}
