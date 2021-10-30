package co.com.sofka.wsscore.domain.library.event;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

import java.time.LocalDate;

public class ResourceCreated extends DomainEvent {

    private final String name;
    private final String resourceType;
    private final String area;
    private final LocalDate date;
    private final Boolean available;

    public ResourceCreated(String name, String type, String area, LocalDate date, Boolean available) {
        super("sofkau.program.resourcecreated");
        this.name = name;
        this.resourceType = type;
        this.area = area;
        this.date = date;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    @Override
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
