package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Types.IntType;
import Model.Values.*;

public class ArithExp implements Exp{
    private Exp e1;
    private Exp e2;
    private final int op; //1-plus, 2-minus, 3-star, 4-divide
    private final char operand;

    public ArithExp(char operand, Exp e1, Exp e2){
        this.operand = operand;
        switch (operand){
            case '+':
                this.op = 1;
                break;
            case '-':
                this.op = 2;
                break;
            case '*':
                this.op = 3;
                break;
            case '/':
                this.op = 4;
                break;
            default:
                this.op = 0;
                break;
        }
        this.e1 = e1;
        this.e2 = e2;
    }

    public Value eval(MyIDictionary<String,Value> tbl) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if(this.op==0) throw  new MyException("Bad operand");
                if(this.op==1) return new IntValue(n1+n2);
                if(this.op==2) return new IntValue(n1-n2);
                if(this.op==3) return new IntValue(n1*n2);
                if(this.op==4)
                    if(n2==0) throw new MyException("division by zero");
                    else return new IntValue(n1/n2);
            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
        return v1;
    }

    @Override
    public String toString() {
        return "ArithExp{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", op=" + this.operand+
                '}';
    }
}
