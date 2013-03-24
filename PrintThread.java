public class PrintThread extends Thread{
	
	long number;
	EventBarrier e;
	
	PrintThread(long num, EventBarrier e) {
		this.number = num;
		this.e = e;
	}
	
	public void run() {
		e.arrive();
		System.out.println(number);
		e.complete();
	}
}
