import java.util.ArrayList;
import java.lang.Math;

public class Caller {
	static ArrayList<Processor> processors = new ArrayList<Processor>();
	static Supervisor s = new Supervisor();
	public static void main(String[] argv) {
		processors.add(new Processor(s,2000));
		Dispatcher dispo = new Dispatcher(processors);
		dispo.setSupervisor(s);
		s.setDispatcher(dispo);
		s.setProcessors(processors);
		while (true){
			dispo.dispatch();
		}
	
	}

}
