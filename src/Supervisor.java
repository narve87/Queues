import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.ArrayList;
//import java.util.logging.*;

public class Supervisor {

	static int processLogCounter=0;
	private ArrayList<Processor> processors;
    private Dispatcher dispatcher;
    //HAHAHAHAHAHAHAHAHAHA

    Supervisor(){
      this.processors = new ArrayList<Processor>();
      }
    
    public void setProcessors(ArrayList<Processor> processors) {
    	this.processors = processors;
    }
    public void setDispatcher(Dispatcher dispatcher) {
    	this.dispatcher = dispatcher;
    }

  public Duration getAverageQueueTime(ArrayList<Process> toCheck) {
	  if(toCheck.size()<0) {
		  Duration total = null;	  
		  for (Process p:toCheck) {
			  total = total.plus(p.getQueueTime());  
		  }
		  return total.dividedBy((long)toCheck.size());
	  }
	  return null;
  }
  
  public boolean assignNewProcessor(ArrayList<Process> finishedProcesses) {
	  if (this.getAverageQueueTime(finishedProcesses).toMillis()>=Duration.ofMillis(4000).toMillis()) {
		  Processor p = new Processor(this,2000);
		  dispatcher.additionalProcessor(p);
		  this.processors.add(p);
		  return true;
	  }
	  return false;
  }
  
  public void log(ArrayList<Process> toLog) throws IOException {
	  if(toLog!=null) {
		  FileWriter fw = new FileWriter("Processes"+ processLogCounter + ".log");
		  processLogCounter++;
		  fw.write("Status for the last " + toLog.size() +  " generated Processes:" + System.lineSeparator());
		  int finished=0;
		  int queued=0;
		  Duration totalQueueTime = Duration.ZERO;
		  Duration totalComputationTime = Duration.ZERO;
		  for (Process p:toLog) {
			  if (p.getStatus()==3) {
				 finished++;
				 totalQueueTime=totalQueueTime.plus(p.getQueueTime());
				 totalComputationTime=totalComputationTime.plus(p.getComputationTime());
		  		}
		  		if (p.getStatus()==2) {
		  			queued++;
		  		}
		  }
		  fw.write("Processes in Queue: " + queued + System.lineSeparator() + "Finished Processes: " + finished + System.lineSeparator() + "Average time in Queue for finished Processes: " + totalQueueTime.dividedBy(finished).toMillis() + "ms" + System.lineSeparator() + "Average computation time for finished processes: " + totalComputationTime.dividedBy(finished).toMillis() + "ms" + System.lineSeparator());
		  fw.close();
	  }
  }
}