import java.time.Instant;

public class Processor {

  private Integer id;

  private Integer status;

  private Integer speed;

  private Queue myQueue;
  
  private Process tmp;
  
  
  Processor(int id){
	this.id = id;
	this.myQueue = new Queue();
	Thread d = new Thread(this::work);
	d.start();
  }
   

  private int calculateProcesstime(Process process) {
	  return process.getCost(); //subject to be changed
  }

  public void report() {
  }

  public void setStartTime() {
  }

  public void setEndTime() {
  }

  private void reportIdleStart() {
	  System.out.println("Processor #" + this.id + " is idle."); 
  }

  private void reportIdleEnd() {
	  System.out.println("Processor #" + this.id + " is no longer idle.");
  }
  
  public void work() {
	  while (true) {
	  tmp = myQueue.getProcess();
	  if (tmp != null) {
	  try {
		  
		  Instant inst = Instant.now();
		  tmp.setStarttime(inst);
		  Thread.sleep(this.calculateProcesstime(tmp));
		  inst = Instant.now();
		  tmp.setEndtime(inst);
	} catch (InterruptedException e) {
		//pass
	}
	  System.out.println(this.id + " : " + this.myQueue.getQueueLength() + " ; " + tmp.getStarttime() + " : " + tmp.getEndtime());
	  //this.reportIdleStart();
    }
	  else {
		  	this.reportIdleStart();
		  	while (this.myQueue.getQueueLength()==0) {
				  try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			  this.reportIdleEnd();
	  }
	  }
  }
  
  public void enqueue(Process process) {
	myQueue.enqueue(process);  
  }
  public int returnTotalQueueCost() {
	  return this.myQueue.returnTotalCost();
  }
}