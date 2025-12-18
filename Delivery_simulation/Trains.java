public class Trains extends Events {
    private double start;
    private double end;
    private boolean isBlocking;
     

    public Trains(double start, double end) { //initaillzing the trains class
        super(start);// the method which was inherited from event class setting that equall to starttime
        this.start = start;
        this.end = end;
        this.isBlocking = true;
    }
    public double getStart() {
        return start;
    }
    public double getEnd() {
        return end;
    }
    public boolean isBlocking() {
        return isBlocking;
    }
    public void setStart(int start) {
        this.start = start;
    }


    public void setEnd(int end) {
        this.end = end;
    }
    public void setNotBlocking() {
        this.isBlocking = false;
        super.setTime(this.end); // Update the event time to the end time
    }

}
