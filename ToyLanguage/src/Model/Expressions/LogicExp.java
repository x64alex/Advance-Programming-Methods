package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
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
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1 = this.e1.eval(tbl, hp);
        Value v2 = this.e2.eval(tbl, hp);
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
        else throw new MyException("Not boolean types in logical expression.");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new BoolType())){
            if(typ2.equals(new BoolType())){
                return new BoolType();
            }
            else throw new MyException("second operand is not a bool");
        }
        else throw new MyException("first operand is not a bool");
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
