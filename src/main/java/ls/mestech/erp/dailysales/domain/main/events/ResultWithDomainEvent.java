package ls.mestech.erp.dailysales.domain.main.events;

public class ResultWithDomainEvent <A,E extends DomainEvent> extends java.lang.Object{
    A	result;
    E events;

    public ResultWithDomainEvent(A result, E events) {
        this.result = result;
        this.events = events;
    }
}
