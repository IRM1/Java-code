import java.util.ArrayList;
public class Character{//decalre all fleids of the character class
    private String name;
    private int credit;
    private ArrayList<Item> items;

    public Character (String name, int credits){//when the character is made this is called and the private fleids are set with the given values in the method
      this.name=name;
      this.credit=credits;
      this.items=new ArrayList<Item>();
    }
    public String getName(){//when called return the name of the chracter on which it is called 
        return name;
    }
    public void setName(String name){//when called set the name of the character on which it is called
        this.name=name;
    }
    public int getcredit(){//when called return credit of the character on which it is called 
        return credit;
    }
    public void setcredit(int credit){//when called sets chracter credit of the chracter on which it is called 
          this.credit=credit;

    }
    public ArrayList<Item> getItems() {//when called return items list of the chracter on which it is called
        return items;
    }
    public void addItem(String itemName,int itemValue){//add an Item to item list of a chracter on which it is called
        items.add(new Item(itemName, itemValue));
    }
    public boolean dropItem(String itemName){//when called and true drops the item from chracter item list
        for(int i=0;i<items.size();i++){//the loop wil run the number of times the length of arraylist items 
            if(items.get(i).getname().equals(itemName)){//then it will one by one get element from item compare it with item name when it will equl the name it will stop and return true
                items.remove(i);//then it will remove that item aswell
                return true;
            
            }
    }
     return false;//else it will return false if there was no element by the name itemName

}
    public boolean sellItemToVendor(String itemName){//when called and true will remove the itemName from items and add the amout of item value to chracter credit
        if(items.toString().contains(itemName)){//checking if item list contains itemName
        int index=gettingindex(items, itemName);//this method will return the index at which itemName is in items 
        Item yolo=items.get(index);//make an Item object and assign it will the index
        int value1=yolo.getvalue();//get value of the item and assigning it with value1
        credit+=value1;//adding the value1 to character credit
        items.remove(index);//removing the itemName from items
        return true;
    }
        return false;//if it does not contain itemName it will retuen false
    }
public boolean sellItemToCharacter(String itemName,Character buyer){//when called and true will remove the item from the Character which called the method and will add the value of that item to its credits,it will also add that item to charater buyer and subtract the value of the item from the chracter credits
    int index=gettingindex(items, itemName);//getting index of itemName from items 
    Item beingsold=items.get(index);//assign it that index to Items object beingsold
    int itemvalue=beingsold.getvalue();//getting value
    int buyermoney=buyer.getcredit();//getting buyer credits
    if(itemvalue<buyermoney){//if excute if the item value is less than buyermoney
        items.remove(index);//it will remove itemName from seller items list
        credit+=itemvalue;//add the value to seller credits
        buyer.setcredit(buyermoney-itemvalue);//this will adjust the buyer credits by subtacting the value of item form its credits
        buyer.getItems().add(beingsold);//will add the item to buyer list
        return true;
    }

    return false;//else this will be excuted
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
public String toString() {//the way this class will be printed whenever called
    return name + " (" + credit + ")";
}
}   
