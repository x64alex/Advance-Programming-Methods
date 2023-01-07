package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl, state.getHeap());
            Type typId = (symTbl.lookup(id)).getType();
            if((val.getType()).equals(typId)){
                symTbl.update(id, val);
            }
            else throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match");
        }
        else throw new MyException("the used variable" + id + " was not declared before");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(this.id,this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }
}
