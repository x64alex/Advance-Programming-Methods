package View;

import Model.PrgState;
import Model.Statments.IStmt;
import Controller.Controller;
import Repository.MyIRepository;
import Repository.MyRepository;

class Interpreter {
    public static void main(String[] args) {
//        IStmt ex1 = new ...;
//        PrgState prg1 = new PrgState(...,ex1);
//        MyIRepository repo1 = new MyRepository(prg1, "log1.txt");


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
       // menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.show();
    }
}