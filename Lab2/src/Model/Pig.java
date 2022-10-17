package Model;

public class Pig implements Animal{
    private int weight;

    public Pig(int weight){
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    public String toString() {
        return "Pig "+"weight: "+ Integer.toString(weight);
    }
}
