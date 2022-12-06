package Model.Statments.RWStatments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.FileTable.MyIFileTable;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statments.IStmt;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;

public class openRFile implements IStmt {

    private Exp exp;

    public openRFile(Exp exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value expression = exp.eval(symTbl, state.getHeap());
        if(expression.getType().equals(new StringType())){
            StringValue newExpression = (StringValue) expression;
            if(!fileTable.isDefined(newExpression)){
                try{
                    BufferedReader buf = new BufferedReader(new FileReader(newExpression.toString()));
                    fileTable.initialize(newExpression,buf);
                }catch (Exception e){
                    throw new MyException(e.toString());
                }
            }
            else{
                throw new MyException("File already exists");
            }
        }
        else {
            throw new MyException("Expression not evaluate to string");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new openRFile(this.exp);
    }

    @Override
    public String toString() {
        return "openRFile of "+exp;
    }
}
