import java.util.Random;
import java.util.Scanner;
public class Memory {
    public static void main(String[] args) throws Exception {
        Random rand=new Random();
        int firstvalue=0;
        int secoundvalue=0;
        int thirdvalue=0;
        int correct=0;
        int score=0;
        int input=0;
        

        for (int i=0;i<10;i++){
            System.out.print("The numbers for this round are ");
        for(int j=0 ;j <3;j++){
            int output=rand.nextInt((100-1)+1)+1;
        System.out.print(output+" ");
        if(j==0){
            firstvalue=output;
        }
        else if(j==1){
            secoundvalue=output;
        }
        else if (j==2){
            thirdvalue=output;
        }
        }
          try{
           Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
       for(int l=0;l<100;l++){
        System.out.println();
       }
        
        int pick=rand.nextInt((3-0)+1)+0;
        String questions[]={"What is the smallest number?","What is the largest Number?","What is the median number?","What is the sum of all number?"};
        System.out.print(questions[pick]);
         Scanner scnr=new Scanner(System.in);
         input=scnr.nextInt();
        if (pick==0){
        if((firstvalue<secoundvalue)&&(firstvalue<thirdvalue)){
                correct=firstvalue;
            }
            else if((secoundvalue<firstvalue)&&(secoundvalue<thirdvalue)){
                correct=secoundvalue;
            }
            else if((thirdvalue<secoundvalue)&&(thirdvalue<firstvalue)){
                correct=thirdvalue;
            }
            if(!(correct==input)){
                System.out.println("That's wrong. The correct answer was "+correct);
            }
            if (correct==input){
                System.out.println("That' right!");
            }
         }
        else if (pick==1){
              if((firstvalue>secoundvalue)&&(firstvalue>thirdvalue)){
              correct=firstvalue;
              }
            else if((secoundvalue>firstvalue)&&(secoundvalue>thirdvalue)){
            correct=secoundvalue;
             }
            else if((thirdvalue>secoundvalue)&&(thirdvalue>firstvalue)){
            correct=thirdvalue;
             }
             if(!(correct==input)){
                System.out.println("That's wrong. The correct answer was "+correct);
            }
            if (correct==input){
                System.out.println("That' right!");
            }
        }
        else if (pick==2){
            correct=secoundvalue;
            if(!(correct==input)){
                System.out.println("That's wrong. The correct answer was "+correct);
            }
            if (correct==input){
                System.out.println("That' right!");
            }

        }
        else if(pick==3){
            correct=firstvalue+secoundvalue+thirdvalue;
            if(!(correct==input)){ 
                System.out.println("That's wrong. The correct answer was "+correct);
            }
            if (correct==input){
                System.out.println("That' right!");
            }
        }
        
        if(correct==input){ 
            score=score+1;
       }
   }
   System.out.println("You got "+score+" out of 10 right");
    }
}





