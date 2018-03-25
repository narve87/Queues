import java.time.Instant;

public class Processor {

	static int counter = 0;
	private Integer id;

	private Integer status;
	private Integer speed;
	private Queue myQueue;
	private Process tmp;
	private Supervisor chef;
	private Instant lastProcessCompleted;
  
	Processor(Supervisor s, int speed){
		counter++;
		this.id = counter;
		this.myQueue = new Queue();
		this.chef = s;
		this.speed = speed;
		this.lastProcessCompleted=Instant.now();
		new Thread(this::work).start();
	}
   

	public Instant getLastProcessCompleted() {
		return this.lastProcessCompleted;
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
	  this.status=1;
  }

  private void reportIdleEnd() {
	  System.out.println("Processor #" + this.id + " is no longer idle.");
	  this.status=0;
  }
  
  public void work() {
	  while (true) {
	  tmp = myQueue.getProcess();
	  if (tmp != null) {
		  tmp.setStatus(2);
		  tmp.setStarttime(Instant.now());
		  try {
			  Thread.sleep(this.calculateProcesstime(tmp));
		} catch (InterruptedException e) {
		//pass
	}
	tmp.setEndtime(Instant.now());
	tmp.setStatus(3);
	System.out.println(this.id + " : " + this.myQueue.getQueueLength() + " ; " + tmp.getStarttime() + " : " + tmp.getEndtime() + " : " + tmp.getComputationTime().toMillis());
	//this.reportIdleStart();
	this.lastProcessCompleted = Instant.now();
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

  public Integer getId() {
	  return this.id;
  }

  public Integer getSpeed() {
	return speed;
  }

  public void setSpeed(Integer speed) {
	this.speed = speed;
  }
}