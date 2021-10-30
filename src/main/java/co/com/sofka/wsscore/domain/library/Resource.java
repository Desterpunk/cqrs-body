package co.com.sofka.wsscore.domain.library;

import co.com.sofka.wsscore.domain.generic.AggregateRoot;
import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventChange;
import co.com.sofka.wsscore.domain.library.event.ResourceCreated;

import java.util.*;


public class Resource extends AggregateRoot implements EventChange {
    private String name;

    public Resource(String resourceId, String name){
        super(resourceId);
        appendChange(new ResourceCreated(name)).apply();
    }

    private Resource(String id){
        super(id);
        subscribe(this);
        listener((ResourceCreated event)-> {
          this.name = event.getName();
        });
    }

    public static Resource from(String id, List<DomainEvent> events){
        var resource = new Resource(id);
        events.forEach(resource::applyEvent);
        return resource;
    }

    public String name() {
        return name;
    }
}