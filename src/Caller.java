import java.util.ArrayList;
import java.lang.Math;

public class Caller {
	static ArrayList<Processor> processors = new ArrayList<Processor>();
	static Supervisor s = new Supervisor();
	public static void main(String[] argv) {
		
		processors.add(new Processor(1,s));
		processors.add(new Processor(2,s));
		processors.add(new Processor(3,s));
		
		Dispatcher dispo = new Dispatcher(processors);
		while (true){
			int index =  1 + (int) (Math.random() * 5);
			for (int i=0; i<index; i++) {
				dispo.dispatch();
			}
		}
	
	}

}
