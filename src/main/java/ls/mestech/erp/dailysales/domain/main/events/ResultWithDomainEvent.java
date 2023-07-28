package ls.mestech.erp.dailysales.domain.main.events;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class ResultWithDomainEvent <A,E extends DomainEvent> extends java.lang.Object{
    A	result;
    E events;

    public ResultWithDomainEvent(A result, E events) {
        this.result = result;
        this.events = events;
    }

    public A getResult() {
        return result;
    }

    public E getEvents() {
        return events;
    }
}
