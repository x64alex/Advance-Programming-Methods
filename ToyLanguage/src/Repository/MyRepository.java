package Repository;

import Model.PrgState;

public class MyRepository implements MyIRepository {
    private PrgState currentState;

    public MyRepository(PrgState state){
        this.currentState = state;
    }

    @Override
    public PrgState getCrtPrg() {
        return currentState;
    }
}
