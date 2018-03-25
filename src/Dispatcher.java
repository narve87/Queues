import java.io.IOException;
import java.util.ArrayList;

public class Dispatcher {

  //private Integer id;
  private ArrayList<Processor> processors;
  private Supervisor supervisor;
  private int countOfDispatchedProcesses;
  private ArrayList<Process> dispatchedProcesses;
  

  Dispatcher(ArrayList<Processor> processors){
	  this.processors = processors;
	  this.countOfDispatchedProcesses=0;
	  this.dispatchedProcesses = new ArrayList<Process>();
  }
  
  public void setSupervisor(Supervisor supervisor) {
	  this.supervisor = supervisor;
  }
  
  @SuppressWarnings("unchecked")
public void dispatch() {
	  
	  Process p = this.generateProcess();
	  this.returnSmallestQueue().enqueue(p);
	  this.countOfDispatchedProcesses++;
	  this.dispatchedProcesses.add(p);
	  
	  try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if((countOfDispatchedProcesses%20)==0) {
		  if (supervisor.removeIdleProcessors((ArrayList<Processor>) processors.clone())) {
			  //System.out.println(processors.size());
		  }
		  if (supervisor.assignNewProcessor(dispatchedProcesses)) {
			  System.out.println("New processor assigned due to long average Queuetimes");
			  //System.out.println(processors.size());
		  }
	  }
	  if (this.countOfDispatchedProcesses>=100) {
		  try {
			supervisor.log(dispatchedProcesses);
		} catch (IOException e) {
			//pass
		}
		  dispatchedProcesses.clear();
		  countOfDispatchedProcesses=0;
	  }
	  
  }
  public boolean additionalProcessor(Processor p) {
	  if(p!=null) {
		  this.processors.add(p);
		  return true;
	  }
	  return false;
  }
  
  public boolean removeProcessor(Processor p) {
	  return processors.remove(p);
  }

  private Process generateProcess() {
	  return new Process((400000 + (int) (Math.random() * 1000000)));
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