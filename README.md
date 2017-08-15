Operating systems TDT4186

P1
It's a bank program were the purpose is mutual exclusion for the courier when they use dipositMoney in the Bank.


P2 barbershop
CustomerQueue in this problem are a circular buffer, 
and we will get the producer/consumer problem. The CustomerQueue is implementet as an mesa monitor.
It's one doorman that has the job as producer (add customers to the customerQueue without making a bufferoverflow/write over the current customer in the given place). The are three barbers that are the consumer (they take the customer out). It's important to have mutual exclusion to prevent emptyqueue exception when the barber is taken a customer out of the queue. 


Oprating systems P3-UTDELT

In this assignment we simulated how a process are being procesed.
This is been done have making different event that are processswitch,
 processesend, I/O request event and I/O end event. Round Robin is the process scheduling algorithm been used.



