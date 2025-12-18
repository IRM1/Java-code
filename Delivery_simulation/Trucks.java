public class Trucks extends Events {
    private int truckNum;
    private String event;
    private double startTime;

    public Trucks(int truckNum, double startTime, String event) { //implements truck class
        super(startTime);// the method which was inherited from event class setting that equall to starttime
        this.truckNum = truckNum;
        this.event = event;
        this.startTime = startTime;
    }
    public int getTruckNum(){
        return truckNum;
    }

    public double getstartTime(){
        return startTime;
    }
    public String getevent(){
        return event;
    }
    public void setEvent(String event){
        this.event=event;
    }

}
