public class Events implements Comparable<Events> {
    private double time;
    public Events(double time){ //implementing the event class
        this.time=time;
    }
    public double getTime(){
        return time;
    }
    public void setTime(double time){
        this.time=time;
    }
    public int compareTo(Events o){
        if(time<o.getTime()){
            return -1;
        } 
        else{
            return 1;
        }
    }
}
