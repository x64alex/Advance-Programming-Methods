package Model.Statments.RWStatments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.FileTable.MyIFileTable;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statments.IStmt;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;

public class readFile implements IStmt{
    private Exp exp;
    private String var_name;

    public readFile(Exp exp, String var_name){
        this.exp = exp;
        this.var_name= var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(!symTbl.isDefined(this.var_name)){
            throw new MyException("Variable not defiened");
        }
        MyIFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value expression = exp.eval(symTbl);
        if(expression.getType().equals(new StringType())){
            StringValue newExpression = (StringValue) expression;
            if(fileTable.isDefined(newExpression)){
                try{
                    BufferedReader buf = new BufferedReader(new FileReader(newExpression.toString()));
                    String line = buf.readLine();
                    Integer value = 0;
                    if(line != null){
                        value = Integer.parseInt(line);
                    }
                    IntValue val = new IntValue(value);
                    symTbl.update(this.var_name,val);
                }catch (Exception e){
                    throw new MyException(e.toString());
                }
            }
            else{
                throw new MyException("File does not exists");
            }
        }
        else {
            throw new MyException("Expression not evaluate to string");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new readFile(this.exp,this.var_name);
    }

    @Override
    public String toString() {
        return "readFile of "+exp;
    }
}
