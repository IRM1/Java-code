import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class packageDelivery {
  public static void main(String[] args) throws Exception {
    //The best pecentange of drone is 47% which is will complete all the deliveries in 2186 minuties
    int TOTAL_PACKAGES = 1500;
    int DIST_TO_TRAIN_CROSSING = 3000;
    int DIST_AFTER_CROSSING = 27000;
    int DRONE_SPEED = 500;
    int DRONE_START_DELAY = 3;
    int TRUCK_SPEED = 30;
    int TRUCK_CAPACITY = 10;
    int TRUCK_START_DELAY = 15;
    float PERCENT_BY_DRONE = 0.47f;//which you will but here as .47f
    double timeTocrossing = DIST_TO_TRAIN_CROSSING / TRUCK_SPEED;
    double timeAftercrossing = DIST_AFTER_CROSSING / TRUCK_SPEED;
    ArrayList<Trucks> allTrucks = new ArrayList<>();
    ArrayList<Trucks> waitList = new ArrayList<>();
    boolean isTrain = false;
    int TOTAL_TRUCKS;
    double simClock = 0;
    int starting = 1;
    double TOTAL_TIME_TRUCK=0;
    double TOTAL_TRIP_TIME=0;

    PriorityQueue<Events> allEvent = new PriorityQueue<>();

    int TOTAL_DRONES = (int) (TOTAL_PACKAGES * PERCENT_BY_DRONE);//calculation of total drones
    if (!(((TOTAL_PACKAGES - (TOTAL_PACKAGES * PERCENT_BY_DRONE)) % TRUCK_CAPACITY) == 0)) {//if there is a reminder of packages this will make one more truck
      TOTAL_TRUCKS = (TOTAL_PACKAGES - TOTAL_DRONES) / TRUCK_CAPACITY + 1;

    } else {
      TOTAL_TRUCKS = (TOTAL_PACKAGES - TOTAL_DRONES) / TRUCK_CAPACITY;
    }
    double DRONE_TIME = (DIST_AFTER_CROSSING+DIST_TO_TRAIN_CROSSING) / DRONE_SPEED; //calculating ONE DRONE TIME
    double TOTAL_DRONE_TIME = DRONE_START_DELAY * (TOTAL_DRONES - 1) + DRONE_TIME;//calculating all drone time
     String percentageDrone=String.format("%.0f", PERCENT_BY_DRONE*100);//formating the percentage drone value so it looks good
    System.out.println("With " + percentageDrone + "% drones and " + TOTAL_PACKAGES +" PACKAGES" +",\nThere will be:");
    System.out.println("TOTAL_TRUCKS " + TOTAL_TRUCKS);

    System.out.println("Total Drone " + TOTAL_DRONES);
    File file = new File("train_schedule.txt");// making a file 
    Scanner reader = new Scanner(file);
    while (reader.hasNextLine()) {  //reading all the lines from that file and parsing them according
      String line = reader.nextLine();
      String[] parts = line.split(" ");
      int start = Integer.parseInt(parts[0]);
      int end = Integer.parseInt(parts[1]);
      int together=start+end;
      allEvent.offer(new Trains(start, together)); //adding all train events to the main priority queue
      if (starting == 1) {
        System.out.println("TRAIN SCHEDULE");
        starting++;
      }
      System.out.println(start + "-" + together);

    }
    reader.close();

    for (int i = 0; i < TOTAL_TRUCKS; i++) { //making all trucks event and adding them to priority queue as well as an arraylist they both will have the same reference variable
      Trucks truck = new Trucks(i, i * TRUCK_START_DELAY, "beginning"); //starting all Truck events
      allEvent.offer(truck);
      allTrucks.add(truck);
    }

    while (!(allEvent.isEmpty())) { //this is were the simulation starts
      Events e = allEvent.poll(); //makes an event e gets the first thing in priroty queue make it equalls this 
      simClock = e.getTime(); //gets the time for that event
      if (e instanceof Trains) { //if that event is a train event this will excute
        Trains train = ((Trains) e); //typecasting it 

        if (train.isBlocking()) { //if the train just arrived this will be true 
          System.out.println(simClock + " Train arrives at crossing");
          train.setNotBlocking(); // this will update the statues of the train now next time this will come in queue it will have a status of going
          allEvent.offer(train); //updated version added back
          isTrain = true; 
        } else { //when train is leaving this will excute
          System.out.println(simClock + " Train leaves crossing");
          int j = 1;
          for (int i = 0; i < waitList.size(); i++) { //  update the trucks which are in waitingtruck andd added them back to queue 
            Trucks waitingTruck = waitList.get(i); 
            waitingTruck.setEvent("crossing");
            waitingTruck.setTime(simClock + j);
            j++;
            allEvent.offer(waitingTruck);
          }
          

          isTrain = false;
        }

      } else if (e instanceof Trucks) { //if instance is of truck
        Trucks truck = ((Trucks) e);//typecasting
        switch (truck.getevent()) { //makles switch statement to update trucks and print imformation as they go through differnt stages by getting thier events
          case ("beginning"): // this stage will add the time required to reach to crossing to the truck and add it back to the queue will print the statues as well 
            System.out.println(simClock + ": TRUCK #" + truck.getTruckNum() + " begins journey");
            truck.setEvent("at_crossing");
            truck.setTime(truck.getTime() + timeTocrossing);
            allEvent.offer(truck);
            break;
          case ("at_crossing"):// will print the statues
            System.out.println(simClock + ": TRUCK #" + truck.getTruckNum() + " waits at crossing");
            if (isTrain) { //if there is train on tracks this will print 
              waitList.add(truck);// add it to the waitlist
            } else {//if not this will and just add the time it need to cross and add it back to the queue
            
              truck.setEvent("crossing");
              truck.setTime(simClock + 1);
              allEvent.offer(truck);
            
            }

            break;
          case ("crossing"):// this will also print the statues and add the time required to reach from the crossing to the destination and add back to the queue
            System.out.println(simClock + ": TRUCK #" + truck.getTruckNum() + " crosses crossing");
            truck.setEvent("done");
            truck.setTime(truck.getTime() + timeAftercrossing);
            allEvent.offer(truck);
            break;
          case ("done")://final stage print out that the trip is completed
            System.out.println(simClock + ": TRUCK #" + truck.getTruckNum() + " completed delivery");
            TOTAL_TRIP_TIME=simClock;//this will store how much time it took for the total trip as this will the last truck to reach the destination
            break;

        }

      }

    }
    System.out.println("STATS");
    for (int i = 0; i < allTrucks.size(); i++) { //will print out all the trucks times one  by one 
      Trucks truck = allTrucks.get(i);
      System.out.println("TRUCK #" + truck.getTruckNum() + " total trip time: " + (truck.getTime() - truck.getstartTime())+"minutes");
      TOTAL_TIME_TRUCK=truck.getTime()+TOTAL_TIME_TRUCK;// will store total time it took for all the trucks to reach thier distination
    }
    
    System.out.println("DRONE TRIP TIME "+DRONE_TIME+" minutes"); //print out DRONE TRIP TIME
    System.out.println("DRONE TOTAL TIME "+TOTAL_DRONE_TIME+" minutes");//DRONE TOTAL TIME
    double AVG_TIME=TOTAL_TIME_TRUCK/TOTAL_TRUCKS;// calculuate the avg time one truck needed to complate journey
    String twoDecimal=String.format("%.2f", AVG_TIME);//formating the string so it prints only 2 decimal place 
    if(TOTAL_TRIP_TIME<TOTAL_DRONE_TIME){ //check which time is greater which ever greater will be the TOTAL TIME TRIP
      TOTAL_TRIP_TIME=TOTAL_DRONE_TIME;
    }
    System.out.println("TRUCK AVG TRIP TIME "+twoDecimal+" minutes");//print out TRUCK AVG TRIP TIME
    System.out.println("TOTAL TIME FOR ALL PACKAGES "+TOTAL_TRIP_TIME+" mintues");//print out TOTAL TIME FOR ALL PACKAGES

  }
}
