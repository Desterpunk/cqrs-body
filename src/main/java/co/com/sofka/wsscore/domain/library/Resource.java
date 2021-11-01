package co.com.sofka.wsscore.domain.library;

import co.com.sofka.wsscore.domain.generic.AggregateRoot;
import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventChange;
import co.com.sofka.wsscore.domain.library.event.ResourceCreated;
import co.com.sofka.wsscore.domain.library.event.ResourceUpdated;
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
        appendChange(new ResourceCreated(name, resourceType, area, date, available)).apply();
    }

    private Resource(String id){
        super(id);
        subscribe(this);
        listener((ResourceCreated event)-> {
          this.name = event.getName();
          this.resourceType = event.getResourceType();
          this.area = event.getArea();
          this.date = LocalDate.parse(event.getDate());
          this.available = event.getAvailable();
        });
        listener((ResourceUpdated event) -> {
            this.name = event.getName();
            this.resourceType = event.getResourceType();
            this.area = event.getArea();
            this.date = LocalDate.parse(event.getDate());
            this.available = event.getAvailable();
        });

    }

    public static Resource from(String id, List<DomainEvent> events){
        var resource = new Resource(id);
        events.forEach(resource::applyEvent);
        return resource;
    }

    public void updateData(String name, String resourceType, String area, LocalDate date, Boolean
                           available)
    {
        appendChange(new ResourceUpdated(name, resourceType, area, date, available)).apply();
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