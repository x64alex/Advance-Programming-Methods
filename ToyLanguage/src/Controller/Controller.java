package Controller;

import Exceptions.MyException;
import Model.ADT.Stack.MyIStack;
import Model.PrgState;
import Model.Statments.IStmt;
import Repository.MyIRepository;
import Repository.MyRepository;

public class Controller implements IController{
    private MyIRepository repo;

    public Controller(MyIRepository myRepository){
        this.repo = myRepository;
    }


    @Override
    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        if(stk.isEmpty()){ throw new MyException("Program State stack is empty");}
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    @Override
    public void allStep() {
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
        //here you can display the prg state
        while (!prg.getStk().isEmpty()) {
            try {
                this.oneStep(prg);
            }catch(MyException e){
                System.out.print(e);
            }
            //here you can display the prg state
            this.displayState();
        }
    }

    @Override
    public void displayState() {
        System.out.print(repo.getCrtPrg());
        System.out.print("\n\n");
    }
}
