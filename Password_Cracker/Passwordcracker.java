import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

public class Passwordcracker2 {
    /*
     * the program run fastest when the numthreads is 13 the time it is average time is 43704ms
     * when it 4 it takes too long 
     * same for three 
     */
    public static void main(String[] args) {
        int numThreads = 13; // number of threads you want it to run
        int keeper = 0;
        long starttime = System.currentTimeMillis();// notes the start time
        ArrayList<String> container = new ArrayList<>();
        Threads[] threadsKeeper = new Threads[numThreads];
        combinationFive("", 0, container); // get the A-Z 5 comibination and store it in container
        long passwordCompleted = System.currentTimeMillis(); // notes time taken to make passwords
        /* prints time taken to make threads */
        System.out.println("The passwords have been made it took " + (passwordCompleted - starttime) + "ms");
        int divider = container.size() / numThreads; // the number of strings divided among each thread
        int remainder = container.size() % numThreads; // the reminder will be stores here
        for (int i = 0; i < numThreads; i++) { // will run until all threads are made
            String fileName = "filecopied" + i + ".zip"; // will make a file name for each thread to check password on
            String contents = "contentsCopied" + i;// will make a content for each thread to store code content in
            try {
                Files.copy(Path.of("protected5.zip"), Path.of(fileName)); // making copies of the file
            } catch (IOException e) {
                e.printStackTrace();
            }
            /* makes an array list to store strings which will given to a thread */
            ArrayList<String> dividerStrings = new ArrayList<>();
            /*
             * will divide the work to each thread
             * keeper will store the index divider is the amount given to each thread
             * remainder will given the remainder to the first thread so that no combination
             * is missed
             * and then will be et to zero
             */
            for (int j = keeper; j < divider + keeper + remainder; j++) {
                dividerStrings.add(container.get(j));// will add the the strings to divider string
                remainder = 0;

            }
            keeper += divider;
            /*
             * makes a new thread store it in threadskeeper eaach thread will have its own
             * arraylist,filename and contents
             * so there wont be any race condition
             */
            threadsKeeper[i] = new Threads(dividerStrings, fileName, contents);
            threadsKeeper[i].start();// starts the thread

        }

        for (int i = 0; i < numThreads; i++) { // will wait until all threads have run
            try {
                threadsKeeper[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
         * Deletes all the files which were made for threads
         */
        for (int i = 0; i < numThreads; i++) {
            String fileName = "filecopied" + i + ".zip";
            String contents = "contentsCopied" + i;
            try {
                Files.delete(Path.of(fileName));
                Files.delete(Path.of(contents + "/message.txt"));
                Files.delete(Path.of(contents));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        long endtime = System.currentTimeMillis();// notes the end time
        System.out.println("Time taken " + (endtime - starttime) + " ms");// prints the end time

    }

    public static void combinationFive(String current, int length, ArrayList<String> container) {
        /*
         * base case if length of the string = 5 this will add it to the continer and
         * return
         */
        if (length == 5) {
            container.add(current);

            return;
        }
        /*
         * run until all 5 chracer words are made
         * ch will add one character one by one to the string current
         * and when the string reaches length 5 base case add it to container and return
         */
        for (char ch = 'a'; ch <= 'z'; ch++) {
            combinationFive(current + ch, length + 1, container);
        }
    }

    public static class Threads extends Thread { // Thread class
        /*
         * checker is static volatile meaning this will be shared among all instanses of
         * thread
         */
        static volatile boolean checker;
        private ArrayList<String> container;
        private String fileName;
        private String contents;

        /* will intialize all threads will the perimeter given */
        public Threads(ArrayList<String> container, String fileName, String contents) {
            this.container = container;
            this.fileName = fileName;
            this.contents = contents;
            checker = true;
        }

        @Override
        public void run() {

            for (String num : container) { // will get string from container one by one
                if (checker) {
                    try {
                        ZipFile zipFile = new ZipFile(fileName);// will make a zip file
                        zipFile.setPassword(num);// check the password
                        zipFile.extractAll(contents);// take out contets and save it on file name contents
                        System.out.println("Successfully cracked!");// print its done
                        System.out.println("The passwords is " + num);// print the password
                        checker = false;// turn it to false so that all threads stop running

                    } catch (ZipException ze) {
                        // System.out.println("Incorrect password :(");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    return;
                }
            }

        }
    }
}
