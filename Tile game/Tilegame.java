import java.util.Scanner;
public class Tilegame{
    public static void main(String[] args) throws Exception {
        System.out.println("Enter the tiles");
        Scanner keyboard= new Scanner(System.in);
        String op1= keyboard.nextLine();
        char a=op1.charAt(0);//made data type char declared variable a assigned it with index 0 from string op1
        char b=op1.charAt(4);//made data type char declared variable b assigned it with index 4 from string op1
        char c=op1.charAt(8);//made data type char decalred varable c assigned it with index 8 from string op1
        char d=op1.charAt(12);//made data type char decalred varable d assigned it with index 12 from string op1
        int points;
        int aaa=Character.getNumericValue(a);//made data type int decalred varable aaa assined it with numerical value of char a
        int ddd=Character.getNumericValue(d);//made data type int decalred varable bbb assined it with numerical value of char b
        int ccc=Character.getNumericValue(c);//made data type int decalred varable ccc assined it with numerical value of char c
        int bbb=Character.getNumericValue(b);//made data type int decalred varable ddd assined it with numerical value of char d
        if (( (a<='3')&&(a>'0')) &&  ((b<='3')&&(b>'0'))  &&((c<='3')&&(c>'0'))&&((d<='3')&&(d>'0'))){//checking if the char a,b,c,d all are less or equall to 3
            if (b==c) {
                points=((aaa+ddd)*2);
                System.out.println("Output: "+a+" | "+b+" , "+c+" | "+d+"->"+points+" points");
            }
            else if (!(b==c)) {//this will excute if char b is not equall char c
                if(d==a) {
                    points=((ccc+bbb)*2);
                    System.out.println("Output: "+c+" | "+d+" , "+a+" | "+b+" -> "+points+" points");
                }
                else if (!(d==a)){//this will excute if char d is not equall char a
                    if ((aaa+bbb)>=(ccc+ddd)){//this will excute if sum of aaa,bbb is greater than or equll to sum of ccc,ddd
                        points=(aaa+bbb);
                        System.out.println("Output: "+a+" | "+b+" -> "+points);
                    }
                    else if ((aaa+bbb)<(ccc+ddd)){//or else this will excute where sum of aaa,bbb is less then sum od ccc,ddd
                        points=(ccc+ddd);
                        System.out.println("Output: "+c+" | "+d+" -> "+points);
                    }
                }
            }
        }
       else {
System.out.println("Output: Invalid input, quitting...");//if char a,b,c,d is greater than 3 this will excute
       }
     
        
    }

}
