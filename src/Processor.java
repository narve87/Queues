import java.time.Instant;

public class Processor {

  private Integer id;

  private Integer status;

  private Integer speed;

  private Queue myQueue;
  
  private Process tmp;
  private Supervisor chef;
  
  
  Processor(int id, Supervisor s, int speed){
	this.id = id;
	this.myQueue = new Queue();
	this.chef = s;
	this.speed = speed;
	new Thread(this::work).start();
  }
   

  private int calculateProcesstime(Process process) {
	  if (this.speed!=0) {
		  return (process.getCost()/this.speed);
	  }
	  return process.getCost(); 
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
		  tmp.setStatus(2);
		  Instant inst = Instant.now();
		  tmp.setStarttime(inst);
		  Thread.sleep(this.calculateProcesstime(tmp));
		  inst = Instant.now();
		  tmp.setEndtime(inst);
		  tmp.setStatus(3);
		  this.chef.handoverProcess(tmp);
	} catch (InterruptedException e) {
		//pass
	}
	  System.out.println(this.id + " : " + this.myQueue.getQueueLength() + " ; " + tmp.getStarttime() + " : " + tmp.getEndtime());
	  //this.reportIdleStart();
    }
	  else {
		  	this.reportIdleStart();
		  	this.status=1;
		  	while (this.myQueue.getQueueLength()==0) {
				  try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			  }
			  this.reportIdleEnd();
			  this.status=0;
	  }
	  }
  }
  
  public void enqueue(Process process) {
	  process.setStatus(1);
	  process.setWorker(this.id);
	  myQueue.enqueue(process);  
	
  }
  public int returnTotalQueueCost() {
	  return this.myQueue.returnTotalCost();
  }
  public int getStatus() {
	  return this.status;
  }


  public Integer getSpeed() {
	return speed;
  }

  public void setSpeed(Integer speed) {
	this.speed = speed;
  }
}