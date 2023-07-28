package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle;

import ls.mestech.erp.dailysales.domain.main.events.DomainEvent;
import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailySalesEventPending implements IState, DomainEvent {
    @Autowired
    private final DailySalesContext dailySalesContext;

    public DailySalesEventPending(DailySalesContext dailySalesContext) {
        this.dailySalesContext = dailySalesContext;
    }

    @Override
    public void Create(DailySalesContext context) {
        //State remains in the same state
    }
    @Override
    public void Update(DailySalesContext context) {
        //State remains in the same state
    }
    @Override
    public void Cancel(DailySalesContext context) throws UnsupportedStateTransitionException {
        throw new UnsupportedStateTransitionException("CANCEL");
    }

    @Override
    public void SendForApproval(DailySalesContext context) throws UnsupportedStateTransitionException {
        throw new UnsupportedStateTransitionException("SEND_FOR_APPROVAL");
    }

    @Override
    public void Approve(DailySalesContext context) throws UnsupportedStateTransitionException {
        throw new UnsupportedStateTransitionException("APPROVE");
    }

    @Override
    public void Decline(DailySalesContext context) throws UnsupportedStateTransitionException {
        throw new UnsupportedStateTransitionException("DECLINE");
    }
}
