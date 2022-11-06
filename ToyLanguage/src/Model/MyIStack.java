package Model;

public interface MyIStack<T>{
    T pop();
    void push(T v);

    Boolean isEmpty();
}