import java.util.ArrayList;

public class Queue {
	
	private static int index = 0;  
	private int id;
	private ArrayList<Process> processList;
	  
	Queue(){
		this.id = index;
		index++;
		this.processList = new ArrayList<>();
	}
	  
	public boolean enqueue(Process process) {
	return processList.add(process);
	}
	  
	public Process getProcess() {
	if (this.processList.size() > 0) {
		Process tmp = this.processList.get(0);
	this.processList.remove(0);
	return tmp;
	}
	return null;
	}
	public int getQueueLength() {
	return this.processList.size();
}
	public int returnTotalCost() {
		int a=0;
		for (Process p:this.processList) {
			a=a+p.getCost();
		}
		return a;
	}
}