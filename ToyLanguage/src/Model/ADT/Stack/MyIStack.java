package Model.ADT.Stack;

import Exceptions.MyException;

public interface MyIStack<T>{
    T pop();
    T peek() throws MyException;
    void push(T v);

    Boolean isEmpty();
}