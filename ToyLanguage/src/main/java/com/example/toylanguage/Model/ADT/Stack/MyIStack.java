package com.example.toylanguage.Model.ADT.Stack;


import com.example.toylanguage.Exceptions.MyException;

public interface MyIStack<T>{
    T pop();
    T peek() throws MyException;
    void push(T v);

    Boolean isEmpty();
}