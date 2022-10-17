package Repository;
import Model.Animal;
import Exceptions.RepoException;

public interface RepositoryInput {
    int getCurrentSize();
    void add(Animal animal) throws RepoException;
    void delete(Animal animal) throws RepoException;
    Animal[] getAll();
}
