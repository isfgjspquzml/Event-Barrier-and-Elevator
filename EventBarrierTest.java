public class EventBarrierTest {

	static int numWorkers = 10;

	public static void main(String[] args) {
		EventBarrier e = new EventBarrier(numWorkers);

		e.raise();

		for(int i=0; i< numWorkers; i++) {
			PrintThread p = new PrintThread(i, e);
			p.start();
		}
	}
}
