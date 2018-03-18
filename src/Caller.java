import java.util.ArrayList;
import java.lang.Math;

public class Caller {
	static ArrayList<Processor> processors = new ArrayList<Processor>();
	static Supervisor s = new Supervisor();
	public static void main(String[] argv) {
		
		processors.add(new Processor(1,s,2000));
		processors.add(new Processor(2,s,1500));
		processors.add(new Processor(3,s,1000));
		
		Dispatcher dispo = new Dispatcher(processors);
		s.setDispatcher(dispo);
		s.setProcessors(processors);
		while (true){
			int index =  1 + (int) (Math.random() * 5);
			for (int i=0; i<index; i++) {
				dispo.dispatch();
			}
		}
	
	}

}
