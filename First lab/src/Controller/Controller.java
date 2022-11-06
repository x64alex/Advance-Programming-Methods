package Controller;
import Repository.RepositoryInput;
import Model.Animal;
import Exceptions.RepoException;

public class Controller {
    private RepositoryInput repository;

    public Controller(RepositoryInput repository) {
        this.repository = repository;
    }

    public void add(Animal animal) throws Exception {
        this.repository.add(animal);
    }
    public void delete(Animal animal) throws Exception {
        this.repository.delete(animal);
    }
    public void getGreaterThan(int weight) {
        Animal[] animals = repository.getAll();
        for(int i=0; i <repository.getCurrentSize();i++) {
            if(animals[i].getWeight()> weight) {
                System.out.println(animals[i]);
            }
        }
    }
}