import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public class wordGame {
    public static void main(String[] args) throws Exception {
       File dictionary=new File("words.game.txt");//reads the inputs from words.game.txt and add it to file name dictinoray
       Scanner word= new Scanner (dictionary);//scanner takes input from file name dictonary 
       ArrayList<String> allwords= new ArrayList<>();
       ArrayList<String> list= new ArrayList<>();
       ArrayList<String> finaleword=new ArrayList<>();
       ArrayList<String> bank=new ArrayList<>();
       int score=0;
       char[] letter={'a'};
       int i=0;
       String display="";
       while(word.hasNext()){//this loop runs until all the words are read from file name dictinary
         allwords.add(word.next());//this keeps on adding those words to Array list name allwords
         i++;//keeping a track how many times the loop ran which will tell how many words are there in allwords
       }
       ArrayList<String> empty=new ArrayList<>();
       ArrayList<String> addletters=new ArrayList<>();
       addletters.add("i");
       while(!empty.equals(addletters)){//this loop keeps on running until a word with all unique letter is picked 
        display=gettheword(allwords,i);//this calls a method named gettheword which will return a string which will be assigned to display
        empty.clear();
         char[] storeletters=display.toCharArray();//converts the string display into storeletter to break the word into letters 
         for(int k=0;k<storeletters.length;k++){//this loop then convert the char to string again and add it to arraylist empty 
             char b=(storeletters[k]);
             String convert=Character.toString(b);
             empty.add(convert);
       }
       addletters.clear();
         for(int k=0;k<storeletters.length;k++){//this loop checks if the letter which is not arraylist addletters if there letter is not there then it will add that letter to it 
             char add=(storeletters[k]);
             String convert=Character.toString(add);
             if(!addletters.contains(convert)){
             addletters.add(convert);
         }   
    }
       }
       letter=display.toCharArray();//this will convert the word which is chosen to char
       for(int j=0;j<letter.length;j++){//this loop will run until all the letters have been added to fineleword
       char b=(letter[j]);
       String s2=Character.toString(b);
       finaleword.add(s2);
       }
       mix(finaleword);//call a method mix which will have a parameter finaleword
       System.out.println("Score: "+score);
       Scanner keyboard=new Scanner(System.in);
       String userinput=keyboard.next();
       while(!userinput.equals("bye")){//loop will keep on running until the user has entred bye
            if(userinput.equals("mix")){//will run if the user has entred mix 
                mix(finaleword);//which will call the method mix
                System.out.println("Score: "+score);
            }
            if(allwords.contains(userinput)){//this will excute when the user has enter the word which was in the array list allword
               if(!bank.contains(userinput)){//this will check if the user has not already entred that word
                ArrayList<String> inputuser=new ArrayList<>();
                char[] letter1=userinput.toCharArray();//convert userinput into char
                for(int j=0;j<letter1.length;j++){//this loop will add all the letters into array list inputuser
                    char add=(letter1[j]);
                    String s3=Character.toString(add);
                    inputuser.add(s3);
                }
                ArrayList <String> store=new ArrayList<>();
                store=trim(finaleword,inputuser);//call a method called trim with 2 paremeters 
                System.out.println(store+"1");
                int arraylistsize=store.size();//this will assign the size of an arraylist which is return by the method trim and assign it with arraylistsize
                int userinputlength=userinput.length();//this assign the length of the word which user has entred to userinputsize
                System.out.println(arraylistsize+"       2      "+userinputlength);
                if(arraylistsize==userinputlength){//if the size both arraylistsize and userinputlength is equall this will excute to score 
                        if(userinputlength==4){//will excute if the user has entred the word with length 4 and will add 1 score to total score
                            score=score+1;
                        }
                        if(userinputlength>4){//will excute if the user has entred the word which has length of more the 4 and will add to the score the same amount as the length
                            score=score+userinputlength;
                        }
                        bank.add(userinput);//this will add the word to arraylist bank 
                    }
                    else {
                        System.out.print("It contains extra letter");
                        System.out.println("Score: "+score);
                    }
                System.out.println("Score: "+score);
            }
            else{//will excute if the word user has given is already give score for 
                System.out.println("User has already entered this word");
                System.out.println("Score: "+score);
            }
            }
            else if (!userinput.equals("mix")&&(!userinput.equals("ls"))&&(!allwords.contains(userinput))){//will exute if the word is not in arraylist userinput 
                System.out.println("Try another word");
                System.out.println("Score: "+score);
            }

            if(userinput.equals("ls")){//will excute if user enter the letters ls with 1 paremeter
                ls(bank);//call a method ls
                System.out.println("Score: "+score);
            }
            userinput=keyboard.next();
       }
    }

    public static void mix(ArrayList<String> takein){//this loop will take the paremeter and shuffle it and print it on main screen
        Collections.shuffle(takein);
        for(int u=0;u<takein.size();u++){
            String oki=takein.get(u);
            System.out.print(oki);
            System.out.print("  ");
    }
    System.out.println();
  }
    public static void ls(ArrayList<String> takein){//this will print out all the words which user has already entred
    for(int i=0;i<takein.size();i++){
        System.out.println(takein.get(i));
    }
  }
    public static String gettheword (ArrayList<String> takein,int i){//takes 2 paremeters from main method 
        String display="";
        Random rand= new Random();
        while(display.length()<7){//this loop keeps on running until a word is choosen which is 7 leter long
         int x=rand.nextInt(i)+1;
        display=takein.get(x);
        }
        return display;//return the a String to the main method with 7 letter
    }
    public static ArrayList<String> trim(ArrayList<String> takein,ArrayList<String> takein2){//this method will make an new arraylist containing all the letters which the user has entred and then will compare with the letter which the user was given will return an arraylist which will only have those letter which were common in both the arraylist
        ArrayList <String> store=new ArrayList<>();
        for(int k=0;k<takein.size();k++){
            String ko=takein.get(k);
            store.add(ko);
        }
        takein2.retainAll(store);
        return takein2;//return Arraylist takein2
    }
}