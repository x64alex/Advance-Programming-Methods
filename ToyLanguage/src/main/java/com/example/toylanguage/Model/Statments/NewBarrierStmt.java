package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.Type;
import javafx.util.Pair;

import java.util.ArrayList;

public class NewBarrierStmt implements IStmt
{
    private String var;
    private Exp expr;
    private Integer nextFree = 0;

    public NewBarrierStmt(String v, Exp e)
    {
        var = v;
        expr = e;
    }

    @Override
    public PrgState execute(PrgState p)
    {
//        try
//        {
//            if (expr.Eval(p.getSymbolT(), p.getHeap()) != 0)
//            {
//                synchronized (p.getCyclicBarrier())
//                {
//                    p.getCyclicBarrier().add(nextFree,new Pair(expr.Eval(p.getSymbolT(), p.getHeap()), new ArrayList<Integer>()));
//                }
//                if (p.getSymbolT().contains(var))
//                    p.getSymbolT().update(var,nextFree);
//                else
//                    p.getSymbolT().add(var,nextFree);
//            }
//            nextFree++;
//            return null;
//        }
//        catch(InexistentSymbolException | DivByZeroException | EvaluationException e)
//        {
//            System.out.println(e.getMessage());
//        }
         return null;
    }

    @Override
    public String toString() {
        return "NewBarrierStmt(" + var + ", "+ expr + ")";
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}
