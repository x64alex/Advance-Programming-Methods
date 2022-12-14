package Model.ADT.Stack;

import Exceptions.MyException;

import java.util.Iterator;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{

    private final Stack<T> internalStack;

    public MyStack() {this.internalStack = new Stack<>();}
    @Override
    public T pop() {
        return internalStack.pop();
    }
    @Override
    public void push(T v) {
        internalStack.push(v);
    }
    @Override
    public Boolean isEmpty() {
        return internalStack.isEmpty();
    }

    @Override
    public T peek() throws MyException {
        if (internalStack.isEmpty())
            throw new MyException("STACK ERROR: Stack is empty");
        return internalStack.peek();
    }
    @Override
    public String toString() {
        String s = "";
        for (T item: internalStack) {
            s = item +"\n"+s;
        }
        return s;
    }
}
