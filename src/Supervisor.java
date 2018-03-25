import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
//import java.util.logging.*;

public class Supervisor {

	static int processLogCounter=0;
    private Dispatcher dispatcher;
    //HAHAHAHAHAHAHAHAHAHA

    Supervisor(){
      }
    
    public void setDispatcher(Dispatcher dispatcher) {
    	this.dispatcher = dispatcher;
    }

    public boolean removeIdleProcessors(ArrayList<Processor> processors) {
    	int removed=0;
    	for (Processor p:processors) {
    		if ((Duration.between(p.getLastProcessCompleted(), Instant.now()).toMillis()>2000)&&p.getStatus()==1){
    			dispatcher.removeProcessor(p);
    			removed++;
    			System.out.println("Processor #" + p.getId() + " was closed due to an extended idletime.");
    		}
    	}
    	if(removed==0) {
    		return false;
    	}
    	return true;
    }
  public Duration getAverageQueueTime(ArrayList<Process> toCheck) {
	  if(toCheck.size()>0) {
		  int processed=0;
		  Duration total = Duration.ZERO;	  
		  for (Process p:toCheck) {
			  if (p.getStatus()>0) {
				  total = total.plus(p.getTimeInQueue());
				  processed++;
			  }
		  }
		  if(processed!=0) {
			  return total.dividedBy(processed);  
		  }
		  
	  }
	  return Duration.ofMillis(8000);
  }
  public Duration getAverageComputationTime(ArrayList<Process> toCheck) {
	  if(toCheck.size()>0) {
		  int processed=0;
		  Duration total = Duration.ZERO;	  
		  for (Process p:toCheck) {
			  if (p.getStatus()>1) {
				  total = total.plus(p.getTimeInComputation());
				  processed++;
			  }
		  }
		  if(processed!=0) {
			  return total.dividedBy(processed);  
		  }
		  
	  }
	  return Duration.ofMillis(3000);
  }
  
  public boolean assignNewProcessor(ArrayList<Process> toCheck) {
	  if (this.getAverageQueueTime(toCheck).toMillis()>=3000) {
		  Processor p = new Processor(this,2000);
		  dispatcher.additionalProcessor(p);
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
		  int inProgress=0;
		  Duration totalQueueTime = Duration.ZERO;
		  Duration totalComputationTime = Duration.ZERO;
		  for (Process p:toLog) {
			  if (p.getStatus()==3) {
				 finished++;
				 totalQueueTime=totalQueueTime.plus(p.getQueueTime());
				 totalComputationTime=totalComputationTime.plus(p.getComputationTime());
		  		}
		  		if (p.getStatus()==1) {
		  			queued++;
		  		}
		  		if (p.getStatus()==2) {
		  			inProgress++;
		  		}
		  }
		  fw.write("Processes in Queue: " + queued + System.lineSeparator() + "Processes currently in progress: " + inProgress + System.lineSeparator() + "Finished Processes: " + finished + System.lineSeparator());
		  fw.write("Average time in Queue: " + this.getAverageQueueTime(toLog).toMillis() + "ms" + System.lineSeparator() + "Average computation time: " + this.getAverageComputationTime(toLog).toMillis() + "ms" + System.lineSeparator());
		  fw.close();
	  }
  }
}