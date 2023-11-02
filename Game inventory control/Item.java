public class Item {//decalre all fleids of the character class
    private String name;
    private int value;
    public Item(String name,int value){//when the character is made this is called and the private fleids are set with the given values in the method
     this.name=name;
     this.value=value;
    }
    public void setName(String name){//set name when called on the item it is called on
        this.name=name;
    } 
    public void setValue(int value){//set value when called of the item it is called on
        this.value=value;
    }
    public String getname(){//return name of the item it is called on
        return name;
    }
    public int getvalue(){//return value of the item it is called on 
        return value;
    }
    public String toString() {//the way this class will be printed when ever called 
        return name + " (" + value + ")";
    }
}
