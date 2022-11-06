package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Values.*;

public class ValueExp implements Exp{
    private Value e;

    public ValueExp(Value e){
        this.e = e;
    }
    public Value eval(MyIDictionary<String,Value> tbl) throws MyException {return e;}
}
