package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Values.*;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op;

    public LogicExp(Exp e1, Exp e2,int op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    public Value eval(MyIDictionary<String,Value> tbl) throws MyException {return new BoolValue();}
}
