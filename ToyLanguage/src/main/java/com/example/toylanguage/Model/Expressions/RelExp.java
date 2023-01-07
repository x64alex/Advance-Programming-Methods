package com.example.toylanguage.Model.Expressions;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Dictionary.MyIDictionary;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Model.Types.*;


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
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new IntType();
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
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
