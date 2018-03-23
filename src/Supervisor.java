import java.util.ArrayList;
import java.util.logging.*;

public class Supervisor {

  private ArrayList<Processor> processors;
    private ArrayList<Process> finishedProcesses;
    private Dispatcher dispatcher;
    //HAHAHAHAHAHAHAHAHAHA

    Supervisor(){
      this.processors = new ArrayList<Processor>();
      this.finishedProcesses = new ArrayList<Process>();
      }
  public void setProcessors(ArrayList<Processor> processors) {
    this.processors = processors;
  }
  public void setDispatcher(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }
public void log() {
  }
  public void handoverProcess(Process p) {
  finishedProcesses.add(p);  
  }
}