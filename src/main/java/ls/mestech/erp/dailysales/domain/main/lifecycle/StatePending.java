package ls.mestech.erp.dailysales.domain.main.lifecycle;
import ls.mestech.erp.dailysales.domain.model.DailySales;

public class PendingState implements ILifeCycle<DailySales,ILifeCycle> {
    @Override
    public void CurrentState(DailySales dailySales) {

    }

    @Override
    public Iterable<ILifeCycle> NextStates(DailySales dailySales) {
        return null;
    }

    @Override
    public void PreviousState(DailySales dailySales) {

    }
}
