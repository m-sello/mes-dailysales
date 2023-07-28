package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle;

import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import org.springframework.stereotype.Service;

@Service
public class DailySalesContext implements IDomainEventState {
    public IState CurrentState;

    @Override
    public IState GetCurrentState() {
        return CurrentState;
    }

    public DailySalesContext() {
        CurrentState = new DailySalesEventPending(this);
    }
    public void Create() throws UnsupportedStateTransitionException {
        CurrentState.Create(this);
    }
    public void Update() throws UnsupportedStateTransitionException {
        CurrentState.Update(this);
    }
    public void Cancel() throws UnsupportedStateTransitionException {
        CurrentState.Cancel(this);
    }
    public void SendForApproval() throws UnsupportedStateTransitionException {
        CurrentState.SendForApproval(this);
    }
    public void Approve() throws UnsupportedStateTransitionException {
        CurrentState.Approve(this);
    }
    public void Decline() throws UnsupportedStateTransitionException {
        CurrentState.Decline(this);
    }


}