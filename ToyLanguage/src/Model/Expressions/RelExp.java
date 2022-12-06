package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelExp implements Exp{

    Exp e1;
    Exp e2;
    String operand;

    public RelExp(String operand, Exp e1, Exp e2) {
        this.operand = operand;
        this.e1 = e1;
        this.e2 = e2;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1 = this.e1.eval(tbl, hp);
        Value v2 = this.e2.eval(tbl, hp);
        IntType intType = new IntType();

        if(intType.equals(v1.getType()) && intType.equals(v2.getType())) {
            IntValue i1 = (IntValue) v1;
            IntValue i2 = (IntValue) v2;
            return switch (operand) {
                case "<" -> new BoolValue(i1.getVal() < i2.getVal());
                case "<=" -> new BoolValue(i1.getVal() <= i2.getVal());
                case "==" -> new BoolValue(i1.getVal() == i2.getVal());
                case ">=" -> new BoolValue(i1.getVal() >= i2.getVal());
                case ">" -> new BoolValue(i1.getVal() > i2.getVal());
                case "!=" -> new BoolValue(i1.getVal() != i2.getVal());
                default -> throw new MyException("Wrong relational operand type");
            };
        }
        else {
            throw new MyException("Not int types in relational expression.");
        }
    }

    @Override
    public String toString() {
        return "LogicExp{" +
                "e1=" + e1 +
                ", op=" + operand +
                ", e2=" + e2 +
                '}';
    }
}
