package Model.Statments;
import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public VarDeclStmt(String name, Type typ){
        this.name = name;
        this.typ = typ;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(! symTable.isDefined(this.name)){
            symTable.initialize(this.name,this.typ.defaultValue());
        }else throw new MyException("Variable already exists");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(this.name, this.typ);
    }

    @Override
    public String toString() {
        return name +"="+ typ;
    }
}
