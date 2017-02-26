package p2;

import java.awt.Dialog.ModalExclusionType;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 * One barber instance corresponds to one consumer in
 * the producer/consumer problem.
 */
public class Barber implements Runnable {
	/**
	 * Creates a new barber. Make sure to save these variables in the class.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	private CustomerQueue queue;
	private Gui gui;
	private int pos;
	private Thread thread;
	private boolean run;
	private boolean free;
	
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		// Incomplete
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
		this.run = true;
		this.free = true;
		
	}

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
		// Incomplete
		while(run){
			synchronized (this) {
			if (this.queue.getSize() != 0){
				Customer customer = this.queue.next(this.pos);
				if(customer != null){
					this.gui.emptyLoungeChair(this.queue.getSize());
					this.gui.fillBarberChair(this.pos, customer);
					this.free = false;
					}
			}
			}
			if(!this.free){
				try {
					Thread.sleep(Globals.barberWork);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gui.emptyBarberChair(this.pos);
				gui.barberIsSleeping(this.pos);
				try {
					Thread.sleep(Globals.barberSleep);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gui.barberIsAwake(this.pos);
				this.free = true;
			
			}
		}
		
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		// Incomplete
		this.thread = new Thread(this);
		this.thread.start();
		
	}

	/**
	 * Stops the barber thread.
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

