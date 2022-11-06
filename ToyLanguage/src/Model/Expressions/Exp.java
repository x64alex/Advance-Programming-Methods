package Model.Expressions;

import Exceptions.MyException;
import Model.MyIDictionary;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl) throws MyException;
}
