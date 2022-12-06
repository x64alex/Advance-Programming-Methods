package Model.Statments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.Stack.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.sql.Ref;
import java.util.Objects;

public class NewStmt implements IStmt{

    String name;
    Exp exp;

    public NewStmt(String name, Exp e) {
        this.name = name;
        this.exp = e;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(this.name)){
            Type typId = (symTbl.lookup(this.name)).getType();
            if(typId instanceof RefType){
                RefType refTypeId = (RefType) typId;
                Type innerType =  refTypeId.getInner();
                Value val = exp.eval(symTbl, state.getHeap());
                System.out.print(val.getType());
                System.out.print(refTypeId.getInner());
                if(Objects.equals(val.getType(), innerType)){
                    int addr = heap.getFreeLocation();
                    heap.initialize(val);
                    symTbl.update(this.name, new RefValue(addr, innerType));
                }
                else throw new MyException("variable " + this.name +"is not of correct inner type\n");
            }
            else throw new MyException("variable " + this.name +" is not of type RefType\n");
        }
        else throw new MyException("the used variable" + this.name + " was not declared before\n");

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(this.name, this.exp);
    }

    @Override
    public String toString() {
        return "new("+this.name+","+this.exp+")";
    }
}
