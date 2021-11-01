package co.com.sofka.wsscore.domain.library.event;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

import java.time.LocalDate;

public class ResourceCreated extends DomainEvent {

    private final String name;
    private final String resourceType;
    private final String area;
    private final String date;
    private final Boolean available;


    public ResourceCreated(String name, String resourceType, String area, LocalDate date, Boolean available) {
        super("sofkau.library.resourcecreated");
        this.name = name;
        this.resourceType = resourceType;
        this.area = area;
        this.date = date.toString();
        this.available = available;
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

    public String getDate() {
        return date;
    }

    public Boolean getAvailable() {
        return available;
    }
}
