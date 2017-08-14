package p2;

import java.util.ArrayList;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue. Make sure to save these variables in the class.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	private Customer[] queue;
	private Gui gui;
	private int queueLength;
	private int nextin;
	private int nextout;
	private int count;
	
    public CustomerQueue(int queueLength, Gui gui) {
    	this.queue = new Customer[queueLength];
    	this.gui = gui;
    	this.queueLength = queueLength;
    	this.nextin = 0;
    	this.nextout = 0;
    	this.count = 0;	
	}
 
	/**
	 * Adds Customer to buffer with mutual exclusion. 
	 * It is implemented as a mesa monitor.
	 * This method is used by the doorman 
	 * @param customer	The customer thats are going to be added to the buffer.
	 */
	public synchronized void add(Customer customer) {
			while (this.count == this.getQueuelength()){
				try {
					this.gui.println("Doorman is waiting for free chairs");
					wait();
					this.gui.println("Doorman war notifyed of free chairs");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.queue[nextin] = customer;
			this.gui.fillLoungeChair(this.nextin, customer);
			this.nextin = (nextin+1)% this.queueLength;
			count++;
			this.notifyAll();		
	}

	/**
	 * takes Customer to the baber and removes it from buffer with mutual exclusion. 
	 * It is implemented as a mesa monitor.
	 * This method is used by the babers 
	 * @param customer	The customer thats are going to be added to the buffer.
	 */
	public synchronized Customer next(int pos) {
		while(this.count == 0){
			try {
				this.gui.println("Barber" + pos + "# is waiting");
				wait();
				this.gui.println("Barber" + pos + "#was notifyed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Customer customer = this.queue[this.nextout];
		this.queue[this.nextout] = null;
		this.gui.emptyLoungeChair(this.nextout);
		this.gui.fillBarberChair(pos, customer);
		nextout =(nextout + 1) % this.queueLength;
		count--;
		this.notifyAll();
		return customer;
	}
	
	
	public int getQueuelength(){
		return this.queueLength;
	}
	
	public int getCount(){
		return this.count;
	}
	
}; 
