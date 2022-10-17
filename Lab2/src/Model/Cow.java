package Model;

public class Cow implements Animal {
    private int weight;

    public Cow(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    public String toString() {
        return "Cow "+"weight: "+ Integer.toString(weight);
    }
}