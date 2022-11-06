package Model.ADT.Stack;

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
}
