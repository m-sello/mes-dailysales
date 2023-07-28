package ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.lifecycle;

import ls.mestech.erp.dailysales.domain.user.dailysalesaggregate.dailysales.exception.UnsupportedStateTransitionException;
import org.springframework.stereotype.Service;

@Service
public interface IState {
    void Create(DailySalesContext context)throws UnsupportedStateTransitionException;
    void Update(DailySalesContext context)throws UnsupportedStateTransitionException;
    void Cancel(DailySalesContext context)throws UnsupportedStateTransitionException;
    void SendForApproval(DailySalesContext context) throws UnsupportedStateTransitionException;
    void Approve(DailySalesContext context) throws UnsupportedStateTransitionException;
    void Decline(DailySalesContext context) throws UnsupportedStateTransitionException;
}
