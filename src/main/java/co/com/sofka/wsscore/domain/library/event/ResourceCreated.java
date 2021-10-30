package co.com.sofka.wsscore.domain.library.event;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

public class ResourceCreated extends DomainEvent {

    private final String name;

    public ResourceCreated(String name) {
        super("sofkau.resource.resourcecreated");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
