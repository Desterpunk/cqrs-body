package co.com.sofka.wsscore.domain.library.event;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

import java.time.LocalDate;

public class ResourceDeleted extends DomainEvent {

    public ResourceDeleted() {
        super("sofkau.resource.resourcedeleted");
    }
}
