public class EventBarrierTest {

	static int numWorkers = 10;

	public static void main(String[] args) {

		EventBarrier e = new EventBarrier(numWorkers);

		for(int i=0; i< numWorkers; i++) {
			System.out.println("---" + i + " ---");
			PrintThread p = new PrintThread(i, e);
			p.start();
			e.raise();
		}
	}
}
