package Model.ADT.List;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {

    private List<T> internalList;
    public MyList() {
        this.internalList = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        internalList.add(element);
    }

    @Override
    public String toString() {
        return "MyList" + internalList;
    }
}
