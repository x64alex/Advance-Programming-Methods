package View;
import Model.*;
import Repository.*;
import Controller.*;

public class Main {
    public static void main(String[] args) {
        Animal a1 = new Pig(2);
        Animal a2 = new Pig(3);
        Animal a3 = new Pig(5);
        Animal a4 = new Cow(6);
        Animal a5 = new Bird(7);


        RepositoryInput repository = new Repository(5);
        Controller controller = new Controller(repository);

        try{
            controller.add(a1);
            controller.add(a2);
            controller.add(a3);
            controller.add(a4);
            controller.add(a5);
            controller.add(a5);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        controller.getGreaterThan(3);

        try {
            controller.delete(a4);
            controller.delete(a4);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        controller.getGreaterThan(3);
    }
}