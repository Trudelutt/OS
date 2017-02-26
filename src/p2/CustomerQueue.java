package p2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue. Make sure to save these variables in the class.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	private ArrayList<Customer>queue;
	private Gui gui;
	private int queueLength;
	
    public CustomerQueue(int queueLength, Gui gui) {
		// Incomplete
    	this.queue = new ArrayList<Customer>();
    	this.gui = gui;
    	this.queueLength = queueLength;

			
    	
	}
 
	public synchronized void add(Customer customer) {
		// Inclomplete
		
			if (this.getSize() == this.getQueuelength()){
				try {
					this.gui.println("Doorman is waiting for free chairs");
					wait();
					this.gui.println("Doorman war notifyed of free chairs");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				this.queue.add(customer);
				this.gui.fillLoungeChair(this.getSize()-1, customer);
				this.notifyAll();
			}		
	}

	public synchronized Customer next(int pos) {
		// Inclomplete
		if(this.getSize() == 0){
		try {
			this.gui.println("Barber" + pos + "# is waiting");
			wait();
			this.gui.println("Barber" + pos + "#was notifyed");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
		Customer customer = this.queue.get(0);
		this.queue.remove(0);
		this.notifyAll();
		return customer;
		}
		return null;
		
		
		
	}
	
	public int getSize() {
		return this.queue.size();
		
	}
	
	public int getQueuelength(){
		return this.queueLength;
	}

	// Add more methods as needed
}; 
