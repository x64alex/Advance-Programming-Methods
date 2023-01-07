package com.example.toylanguage.Model.Statments.RWStatments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.FileTable.MyIFileTable;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Types.StringType;
import com.example.toylanguage.Model.Values.StringValue;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Model.Types.*;


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
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (!exp.typecheck(typeEnv).equals(new StringType()))
            throw new MyException("ERROR: openRFile requires a string expression");
        return  typeEnv;
    }

    @Override
    public String toString() {
        return "openRFile of "+exp;
    }
}
