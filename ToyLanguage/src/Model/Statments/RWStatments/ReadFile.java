package Model.Statments.RWStatments;

import Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statments.IStmt;
import Model.Values.Value;

public class ReadFile implements IStmt{
    private Exp exp;
    private Value var_name;

    public ReadFile(Exp exp, Value var_name){
        this.exp = exp;
        this.var_name= var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(this.exp,this.var_name);
    }
}
