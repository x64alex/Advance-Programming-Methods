package Model.ADT.Dictionary;

public class MyDictionary<T,V> implements MyIDictionary{
    public MyDictionary(){

    }

    @Override
    public V lookup(Object id) {
        return null;
    }

    @Override
    public boolean isDefined(Object id) {
        return false;
    }

    @Override
    public void update(Object id, Object val) {

    }
}
