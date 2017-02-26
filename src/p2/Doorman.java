package p2;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 * One doorman instance corresponds to one producer in
 * the producer/consumer problem.
 */
public class Doorman implements Runnable {
	/**
	 * Creates a new doorman. Make sure to save these variables in the class.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	private CustomerQueue queue;
	private Gui gui;
	private boolean run;
	private Thread thread;
	
	public Doorman(CustomerQueue queue, Gui gui) { 
		// Incomplete
		this.queue = queue;
		this.gui = gui;
		this.run = true;
	}

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
		// Incomplete
		
		while(run){	
			Customer customer = new Customer();
			this.queue.add(customer);
			try {
				Thread.sleep(Globals.doormanSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	/**
	 * Starts the doorman running as a separate thread. Make
	 * sure to create the thread and start it.
	 */
	public void startThread() {
		// Incomplete
		this.thread = new Thread(this);
		this.thread.start();
		
	}

	/**
	 * Stops the doorman thread. Use Thread.join() for stopping
	 * a thread.
	 */
	public void stopThread() {
		// Incomplete
		this.run = false;
		try{
			this.thread.join();
		} catch (InterruptedException e) {
			// This should not happen.
			e.printStackTrace();
		}
		
	}

	// Add more methods as needed
}
