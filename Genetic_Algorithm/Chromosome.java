import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {// this class inherit the Item class which means this class can excess the filds of Item class
                                                                                  
    private static Random rng = new Random();

    public Chromosome() {

    }

    public Chromosome(ArrayList<Item> items) {// when chromosome is created in main this will be called which will call every item in the items arraylist and set its including field randomly to true or false
                                           
        for (int i = 0; i < items.size(); i++) { // runnning the loop throughe the arraylist
            Item changingincluding = (items.get(i)); // getting the item adding it to changingincluding
            changingincluding.setIncluded(rng.nextBoolean());// will randomly set it feild to true or false by using rng
                                                            
            this.add(new Item(changingincluding)); 
        }
    }

    public Chromosome crossover(Chromosome other) { // when this is called from main there will be 2 chromosome envloved one which will call it and the other which is being pass in asone which will call it and the other which is being pass in as
                                               
        Chromosome child = new Chromosome();// new chromosome being made
        for (int i = 0; i < this.size(); i++) { // runnning the loop throughe the arrylist
            int chosing2 = rng.nextInt(10) + 1;// choosing a random number between 1-10
            if (chosing2 <=5) {// if number less than 5 the choromosome which called this method item will be  added to new chormosome
                             
                child.add(new Item(this.get(i)));
            }
            if (chosing2 > 5) {// if number more than 5 the choromosome which was passed in item will be added to new chormosome
                              
                child.add(new Item(other.get(i)));
            }
        }
        return child;// will return the new chromosome

    }

    public void mutate() { // chromsome on which this has been called will set its flied included to opposite of whatever it was before
                          
        for (Item item : this) {// running a loop to check every item in the array list
            int mutation = rng.nextInt(10) + 1;// choosing random number between 1-10
            if (mutation == 1) {// if 1 it will set the item flied included to opposite
                item.setIncluded(!item.isIncluded());
            }

        }
    }

    public int getFitness() { // determing fitness of every Item arraylist
        int totalValue = 0;
        double totalweight = 0;
        for (Item item : this) {// running a loop to check every item in the array list
            if (item.isIncluded()) { // if the item incuded filed is true then this will excute
                totalweight = item.getWeight() + totalweight;// will call weight of that item and add it to locally
                                                             // stored varaible totalweight
                totalValue = item.getValue() + totalValue;// this will do the same but with value of that item
            }

        }
        if (totalweight > 10) {// if weight goes above 10, 0 will be returned
            return 0;
        } else { // otherwise the value of that arraylst will be returned
            return totalValue;
        }
    }

    @Override
    public int compareTo(Chromosome other) {// comparing every item fitness and arranging it
        if (this.getFitness() > other.getFitness()) { // if this fitness is grater than the other it will be other will before this
                                                    
            return -1;
        }
        if (this.getFitness() == other.getFitness()) { // if both same nothing will happen
            return 0;
        } else { // if this fitness is grater than this will be before other
            return +1;
        }
    }

    @Override
    public String toString() {// this is the string method for choromsome

        StringBuilder endresult = new StringBuilder(); // makes a new string builder
        for (Item item : this) { // running a loop to check every item in the array list
            if (item.isIncluded()) { // if that item field is true then it will call the to string method for that item and add it to the string endresult
                                     
                endresult.append("\n" + item.toString());
            }
        }
        endresult.append("\n" + "This Chromosome has a fitness of " + this.getFitness());// after every item is added to endresult which has a true fled this will also be added
                                                                                        

        return endresult.toString();// this will return the string
    }

}
