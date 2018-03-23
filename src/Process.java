import java.time.Duration;
import java.time.Instant;

public class Process {

    private static Integer index = 0;
    private Integer id;
    private Integer status;
//    private Integer priority;
    private Integer cost;
    private Instant enqueuetime;
	private Instant starttime;
    private Instant endtime;
    private int worker;

	
    Process(int cost){
		this.id=index;
		index++;
		this.cost = cost;
		this.setStatus(0);
	}
    public int getId() {
    	return this.id;
    }
	public Instant getStarttime() {
		return starttime;
	}

	public void setStarttime(Instant starttime) {
		this.starttime = starttime;
	}

	
	public Duration getComputationTime() {
		return Duration.between(this.starttime, this.endtime);
	}
	
	public Duration getQueueTime() {
		return Duration.between(this.enqueuetime, this.starttime);
	}
	
	public Duration getTotalTime() {
		return Duration.between(this.enqueuetime, this.endtime);
	}
	public Instant getEndtime() {
		return endtime;
	}

	public void setEndtime(Instant endtime) {
		this.endtime = endtime;
	}
	
    public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public int getWorker() {
		return worker;
	}
	public void setWorker(int worker) {
		this.worker = worker;
	}
	public Instant getEnqueuetime() {
		return enqueuetime;
	}
	public void setEnqueuetime(Instant enqueuetime) {
		this.enqueuetime = enqueuetime;
	}
    

}