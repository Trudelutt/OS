package barbershop;

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
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
		this.run = true; //check if the thread is running
		this.free = true; //check if the barber is free to take in another customer
		
	}

	/**
	 * This is the code that will run when a new thread is
	 * created for this instance.
	 */
	@Override
	public void run(){
		while(run){
			this.queue.next(this.pos);
			this.free = false;
			if(!this.free){
				try {
					Thread.sleep(Globals.barberWork);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gui.emptyBarberChair(this.pos);
				gui.barberIsSleeping(this.pos);
				try {
					Thread.sleep(Globals.barberSleep);
				} catch (InterruptedException e) {
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
		this.thread = new Thread(this);
		this.thread.start();
		
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		this.run = false;
		try{
			this.thread.join();
		} catch (InterruptedException e) {
			// This should not happen.
			e.printStackTrace();
		}
	}
}

