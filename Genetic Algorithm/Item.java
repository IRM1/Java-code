public class Item {// makes an item class and declare its feilds
    private final String name;
    private final double weight;
    private final int value;
    private boolean included;

    public Item(String name, double weight, int value) {// initialize the feilds
        this.name = name;
        this.weight = weight;
        this.value = value;
        included = false;
    }

    public Item(Item other) {// this is another item which has the same filds as item
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    // these are the getters method for item class
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public boolean isIncluded() {
        return included;
    }

    // these are the setter for item
    public void setIncluded(boolean included) {
        this.included = included;
    }

    // a string method
    @Override
    public String toString() {
        return name + " (" + weight + " lbs, $" + value + ")";
    }

}