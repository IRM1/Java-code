import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws Exception {
    String[] options={"1. Create a character","2. Character adds an item","3. Character drops an item","4. Character sells an item to a vendor","5. Character sells an item to another character","6. List characters","7. List a character's inventory by value","8. List all characters' inventories by value","9. Quit"};
    ArrayList <Character> storage= new ArrayList<>();//an arraylist of class character
    Scanner key=new Scanner(System.in);
    int main=menu(options,key);//calls a method and which prints out the options and get the commad from user
    while(main!=9){//will keep on running until user gives commad of 9
    if(main==1){
    System.out.println("What is the character's name?");
    String name=key.nextLine();
    if(!storage.toString().contains(name)) {//this will check if the character being added is unique
        System.out.println("How many credits does the character have?");
        int credits=Integer.parseInt(key.nextLine()) ;
        storage.add(new Character(name, credits));//this will add the character to arraylist storange which is made of class chracter so 2 arguments will be pass string which is name and int will be creidts
        System.out.println(name+" added");
               }
               else{//if chracter not unique this will excute
                System.out.println("Character already exits");
               }
          main=menu(options, key);//call the method again printing the options and getting the user commad
         }
         if(main==2){
            System.out.println("Which character is adding the item?");
            String check=key.nextLine();
            if(storage.toString().contains(check)){//check if the chracter the user is tring to add an item to exits
                System.out.println("What is the name of the item?");
                String inventoryname=key.nextLine();
                System.out.println("What is the item's value?");
                int inventoryvalue=Integer.parseInt(key.nextLine()) ;
                int index=gettingindex(storage, check);//this will call a method hich will give back an index of where the chracter name is stored in arraylist storage
                Character addeditem=storage.get(index);//this will get the character from arraylist storage at index and will store it in addeditem
                System.out.println(addeditem + " is getting an item");
                addeditem.addItem(inventoryname, inventoryvalue);//will add the item created to the chracter item list in chracter class with the argument of the value of inventpry and the name of inventory
                System.out.println(check+" has acquired "+inventoryname);
            }
            else{//if the chracter doesnt exist this will be excuted
                System.out.println("Error: no character with that name exists.");
            }
           main=menu(options, key);
         }
         if(main==3){
            System.out.println("Which items character wants to drop");  
            String drop=key.nextLine();
            System.out.println("Which character is dropping the item");
            String characterdrop=key.nextLine();
            int index=gettingindex(storage, characterdrop);//get the index at which chracterdrop is in Arraylist Storage
            Character droppingitem=storage.get(index);//make character object by taking a character from Arraylist storage at index 
            boolean check=droppingitem.dropItem(drop);//calls a method in class character which if requirment meet will drop the item which will return boolean type back
            if(check){//if all requirment meet
               System.out.println(characterdrop+" has dropped "+drop);
            }
            else if (!check){//if requirment are not meet
               System.out.println(characterdrop+" has not dropped "+drop);
            }
            main=menu(options, key);
     } if(main==4){
        System.out.println("Which charcter wants to sell the item");
        String player=key.nextLine();
        System.out.println("Which item item does chracter wamts to sell to vendor");
        String itemvendor=key.nextLine();
        int index=gettingindex(storage, player);//get the index at where player is in arraylist storage
        Character itemseller=storage.get(index);
        boolean check=itemseller.sellItemToVendor(itemvendor);//call a method in character class and the method will return the type boolean 
        if(check){//this will excute if all requirments meet
            System.out.println(player+" has sold "+itemvendor+" to a vendor");
        }
        else if (!check){//else this will
            System.out.println(player+ " has not sold "+itemvendor+" to a vendor");
        }
         main=menu(options,key);
      }
         if(main==5){
            System.out.println("Which character is selling the item?");
            String seller=key.nextLine();
            System.out.println("Which character is buying the item? ");
            String buyer=key.nextLine();
            System.out.println("What is the name of the item? ");
            String nameofinvetory=key.nextLine();
            if(storage.toString().contains(buyer)&&storage.toString().contains(seller)){//check if storage has buyer and seller then if will excute
                    int index=gettingindex(storage, seller);//getting index where seller is in storage
                    Character seller1=storage.get(index);//make the charater
                    int index2=gettingindex(storage, buyer);//getting index where buyer is in storage
                    Character buyer1=storage.get(index2);//make the character 
                    boolean check=seller1.sellItemToCharacter(nameofinvetory, buyer1);//it will call a method in character class the chracter calling the is seller and it has 2arguments one string of nameofinventory and second is chracter it self which is buyer and will return a boolean
                    if(check){//if boolean is check returns true

                        System.out.println(seller+" has sold "+nameofinvetory+" to "+buyer1.getName());
                    }
                    else if(!check){//if comes back false
                        System.out.println(seller+" could not sell "+nameofinvetory+" to "+buyer1.getName());
                    }
            
         }
         else{
            System.out.println("No such seller or buyer exists");
         }
         main=menu(options,key);
        }
        if(main==6){//simply
            for(int i=0;i<storage.size();i++){//will print all the elements in arraylist storage one by one 
                System.out.println(storage.get(i).toString());
                }
               main=menu(options,key);
             }
      if (main==7){//this will ask user which character inventory they want to print and then it wiil print their inventory
        System.out.println("Which chracter inventory to you want to view");
        String playername=key.nextLine();
        int index=gettingindex(storage, playername);
        Character player=storage.get(index);
        for(int i=0;i<player.getItems().size();i++){//this will call items arraylist of chracter player from chracter class and then will start printing out its inventory one by one
            System.out.println(player.getItems().get(i));
        }
        main=menu(options,key);
      }
      if(main==8){//will print one all characters and all their inventory
        for(int i=0;i<storage.size();i++){//will pick up chracter from strorage print it out one by one
            Character onebyone=storage.get(i);
            System.out.println(onebyone);
            for(int j=0;j<onebyone.getItems().size();j++){//then call its item arraylist from chracter class and print it out one by one
                System.out.println(onebyone.getItems().get(j));
            }
        }
        main=menu(options,key);
      }
    }
}
    public static int menu(String[] excute,Scanner key){//this method print out the oprtion/menu and get the user input and sends it to main method
        for(int i=0;i<excute.length;i++){//printing arraylist elements one by one 
            System.out.println(excute[i]);
        }
        System.out.println("What would you like to do");
        int main=Integer.parseInt(key.nextLine());
         return main;

    }
    public static int gettingindex(ArrayList storage,String gettingthechracter){//this gets the index of the argument of string pass to it
        int index =0; 
        for(int i=0;i<storage.size();i++){//will excute the times the size of arraylist
            if(storage.get(i).toString().contains(gettingthechracter)){//if arraylist contains gettingthechracter it will excute
                index=i;//take at which index it was
                break;//loop will stop
            }
            else{//if gettingthechracter is not in arraylist it will return -1
                index=-1;
            }
        }
        return index;
    }
}