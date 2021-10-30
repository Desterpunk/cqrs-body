package co.com.sofka.wsscore.domain.library;

import co.com.sofka.wsscore.domain.generic.AggregateRoot;
import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventChange;
import co.com.sofka.wsscore.domain.library.event.ResourceCreated;

import java.time.LocalDate;
import java.util.*;


public class Resource extends AggregateRoot implements EventChange {
    private String name;
    private String resourceType;
    private String area;
    private LocalDate date;
    private Boolean available;

    public Resource(String resourceId, String name, String resourceType, String area, LocalDate date, Boolean available){
        super(resourceId);
        appendChange(new ResourceCreated(name,resourceType,area,date, available)).apply();
    }

    private Resource(String id){
        super(id);
        subscribe(this);
        listener((ResourceCreated event)-> {
          this.name = event.getName();
        });
    }

    public static Resource from(String id, List<DomainEvent> events){
        var program = new Resource(id);
        events.forEach(program::applyEvent);
        return program;
    }

    public String name() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getArea() {
        return area;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getAvailable() {
        return available;
    }
}