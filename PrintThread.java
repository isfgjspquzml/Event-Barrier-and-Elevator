public class PrintThread extends Thread{
	
	long number;
	PrintThread(long num) {
		this.number = num;
	}
	
	public void run() {
		System.out.println(number);
	}
}
