package Model;

public class Bird implements Animal {
    private int weight;

    public Bird(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }
    public String toString() {
        return "Bird "+"weight: "+ Integer.toString(weight);
    }
}
