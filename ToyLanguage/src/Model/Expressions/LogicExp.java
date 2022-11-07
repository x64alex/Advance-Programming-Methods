package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Types.BoolType;
import Model.Values.*;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    String operand;

    public LogicExp(String operand,Exp e1, Exp e2){
        this.operand = operand;
        this.e1 = e1;
        this.e2 = e2;
    }
    public Value eval(MyIDictionary<String,Value> tbl) throws MyException {
        Value v1 = this.e1.eval(tbl);
        Value v2 = this.e2.eval(tbl);
        BoolType boolType = new BoolType();

        if(boolType.equals(v1.getType()) && boolType.equals(v2.getType())) {
            BoolValue b1 = (BoolValue) v1;
            BoolValue b2 = (BoolValue) v2;
            return switch (operand) {
                case "and" -> new BoolValue(b1.getVal() && b2.getVal());
                case "or" -> new BoolValue(b1.getVal() || b2.getVal());
                default -> throw new MyException("Wrong logical operand type");
            };
        }
        else {
            throw new MyException("Not boolean types in logical expression.");
        }
    }

    @Override
    public String toString() {
        return "LogicExp{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", op=" + operand +
                '}';
    }
}
