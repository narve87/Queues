import java.util.ArrayList;

public class Dispatcher {

  private Integer id;
  private ArrayList<Processor> processors;
  
  private int lastprocessor = 0;

  Dispatcher(ArrayList<Processor> processors){
	  this.processors = processors;
  }
  
  public void dispatch() {
	  this.returnSmallestQueue().enqueue(this.generateProcess());
	  try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  

  private Process generateProcess() {
	  int processCost = 400000 + (int) (Math.random() * 1000000);
	  return new Process(processCost);
  }

  public Processor returnSmallestQueue() {
	  int smallestCost=this.processors.get(0).returnTotalQueueCost();
	  Processor processorWithSmallestQueue = this.processors.get(0);
	  for (Processor p:processors) {
		  if (p.returnTotalQueueCost()<smallestCost) {
			  smallestCost=p.returnTotalQueueCost();
			  processorWithSmallestQueue=p;
		  }
	  }
	  return processorWithSmallestQueue;
  }
}