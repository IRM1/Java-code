import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneticAlgotithm {
    public static ArrayList<Item> readData(String filename) throws FileNotFoundException { // this method will return an arraylist of items and  passed in with a string  which is a file name and will throw an excepption  if file is not found
                                                                                            
                                                                                            
        ArrayList<Item> items = new ArrayList<>();

        Scanner key = new Scanner(new File(filename));
                                                      
        while (key.hasNextLine()) {// this loop will run until the file has lines
            String things = key.nextLine(); // 
            String[] store = things.split(","); // a new Array of String is being made which will store diffeernt parts of the string things and it will do that by spliting the string things at ","
                                              
            if (store.length == 3) { // the string things had 3 parts which split and stored in store therefore making the lenth of store to 3
                                    
                String name = store[0].trim();// now this will take whatever is at index 0 of store which will be a string and store it in string name and will remove what ever is after it
                                            
               
                double weight = Double.parseDouble(store[1].trim());// this will take whatever is at index 1 of store which  will be a double and store it in weight will remove whatever is it after it
                                
                
                int value = Integer.parseInt(store[2].trim());// this will take whatever is at index 2 of store which will be a int and store it in value and will remove whatever is after it
                                                  
                items.add(new Item(name, weight, value)); // finally this will add a new item to the item arraylist with the filelds which we just split and stored in differnt varaibles
                                                          
            }
        }

        return items;// will return the arraylist
    }

    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize) { // the method will give arraylist of chormosome and in arraylistof items and int which will determnine the size of the arraylist
                                                                                                     
        ArrayList<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Chromosome chromosome = new Chromosome(items);// make a deep copy of items and store it in array list
                                                          
            population.add(chromosome);
        }
        return population;
    }

    public static void main(String[] args) throws Exception { // main method
        ArrayList<Item> items = readData("more_Items.txt"); // calls the method readdata and pass the file name
        ArrayList<Chromosome> currentpopulation = initializePopulation(items, 10); 
                                                                                   
        ArrayList<Chromosome> nextgenration = new ArrayList<>();
        for (int i = 0; i < 20; i++) { // these will tun for 20 times to get a better outcome

            for (Chromosome item : currentpopulation) {// runs the loops makes deep copes from currentpopulation and stroe it in nextgenaration
                nextgenration.add(new Chromosome(item));
            }
            Collections.shuffle(currentpopulation);
            for (int j = 0; j < currentpopulation.size(); j++) {//perfroms croosover and add it to arraylist nextgenaration
                Chromosome parent = new Chromosome(currentpopulation.get(j));       
                nextgenration.add(parent.crossover(currentpopulation.get(j++)));
            }
            Collections.shuffle(nextgenration); // this will randomize nextgenration
            int tenpercent = (int) (nextgenration.size() * 10) / 100;// takes 10 percent from next genaration
            for (int k = 0; k < tenpercent; k++) {// this will run a loop and get choromosome from next genration and call mutate on them
                                                
                nextgenration.get(k).mutate();
            }
            Collections.sort(nextgenration);// sort the arraylist nextgeration

            currentpopulation.clear();// remove everthing from curretpopulation
            for (int l = 0; l < 10; l++) { // runs a loop 10 times
                currentpopulation.add(nextgenration.get(l)); // this will pick first 10 chromosme from nextgenration and add it to currentpopulation
                                                          
            }
        }

        Collections.sort(currentpopulation); // sort the current population

        System.out.println(currentpopulation.get(0));// print the current population
    }
}