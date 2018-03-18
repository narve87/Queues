import java.time.Instant;

public class Process {

    private static Integer index = 0;
    private Integer id;
    private Integer status;
    private Integer priority;
    private Integer cost;
	private Instant starttime;
    private Instant endtime;

    private Queue queue;
	
    Process(int cost){
		this.id=index;
		index++;
		this.cost = cost;
	}

	public Instant getStarttime() {
		return starttime;
	}

	public void setStarttime(Instant starttime) {
		this.starttime = starttime;
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
    

}