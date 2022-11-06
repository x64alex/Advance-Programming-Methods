package Repository;
import Exceptions.RepoException;
import Model.Animal;

public class Repository implements RepositoryInput{

    private int maxSize;
    private int currentSize;
    private Animal[] animals;

    public Repository(int maxSize){
        this.currentSize = 0;
        this.maxSize = maxSize;
        this.animals = new Animal[maxSize];
    }

    @Override
    public int getCurrentSize(){ return this.currentSize; }

    @Override
    public void add(Animal animal) throws RepoException {
        if(this.currentSize < this.maxSize) {
            this.animals[this.currentSize] = animal;
            this.currentSize ++;
        }
        else {
            throw new RepoException("Maximum size of elements reached");
        }
    }

    @Override
    public void delete(Animal animal) throws RepoException{
        for(int i = 0; i < this.currentSize; i++ ) {
            if(this.animals[i].toString().equals(animal.toString())) {
                this.currentSize --;
                for( int j = i; j<this.currentSize; j++) {
                    this.animals[j] = this.animals[j+1];
                }
                return;
            }
        }
        throw new RepoException("Animal does not exist in the farm");

    }

    @Override
    public Animal[] getAll() {
        return this.animals;
    }
}
