package com.example.toylanguage.Model.ADT.Stack;


import com.example.toylanguage.Exceptions.MyException;

import java.util.List;

public interface MyIStack<T>{
    T pop();
    T peek() throws MyException;
    void push(T v);

    List<T> getElements();

    Boolean isEmpty();
}