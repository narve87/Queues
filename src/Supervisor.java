import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.logging.*;

public class Supervisor {

  private ArrayList<Processor> processors;
    private ArrayList<Process> finishedProcesses;
    private Dispatcher dispatcher;
    //HAHAHAHAHAHAHAHAHAHA

    Supervisor(){
      this.processors = new ArrayList<Processor>();
      this.finishedProcesses = new ArrayList<Process>();
      new Thread(() -> {
		try {
			log();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}).start();
      }
  public void setProcessors(ArrayList<Processor> processors) {
    this.processors = processors;
  }
  public void setDispatcher(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void log() throws IOException {
	@SuppressWarnings("resource")
	FileWriter fw = new FileWriter("Processes.txt");
	
	while (true) {
	//	System.out.println(finishedProcesses.size());
	//	if (true) {
		if(this.finishedProcesses.size()>=8) {
			System.out.println("Gotcha");
			for (int i=0; i<5; i++) {
				if (finishedProcesses.get(i)!=null) {
					fw.write("Process #" + finishedProcesses.get(i).getId() + " was in Queue for " + finishedProcesses.get(i).getQueueTime().toMillis() + "ms." + System.lineSeparator());
					finishedProcesses.remove(finishedProcesses.get(i));
				}
			} 
		}
	}
  }
  public void handoverProcess(Process p) {
  this.finishedProcesses.add(p);  
  }
}