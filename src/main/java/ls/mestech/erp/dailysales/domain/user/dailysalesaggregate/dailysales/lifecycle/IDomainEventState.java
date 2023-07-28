package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle;

import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import org.springframework.stereotype.Service;

@Service
public interface IDomainEventState {
    void Create() throws UnsupportedStateTransitionException;
    void Update() throws UnsupportedStateTransitionException;
    void Cancel() throws UnsupportedStateTransitionException;
    void SendForApproval() throws UnsupportedStateTransitionException;
    void Approve() throws UnsupportedStateTransitionException;
    void Decline() throws UnsupportedStateTransitionException;

    IState GetCurrentState();
}
