package Model.Statments.RWStatments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.FileTable.MyFileTable;
import Model.ADT.FileTable.MyIFileTable;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statments.IStmt;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class closeRFile implements IStmt {
    private Exp exp;

    public closeRFile(Exp exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value expression = exp.eval(symTbl, state.getHeap());
        if(expression.getType().equals(new StringType())){
            StringValue newExpression = (StringValue) expression;
            if(fileTable.isDefined(newExpression)){
                BufferedReader buf = fileTable.lookup(newExpression);
                try{
                    buf.close();
                    fileTable.delete(newExpression);
                }catch (Exception e){
                    throw new MyException(e.toString());
                }
            }
            else{
                throw new MyException("Delete nonexistent file");
            }
        }
        else {
            throw new MyException("Expression not evaluate to string");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new closeRFile(this.exp);
    }

    @Override
    public String toString() {
        return "closeRFile of "+exp;
    }
}
