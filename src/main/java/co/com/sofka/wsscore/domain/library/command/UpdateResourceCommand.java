package co.com.sofka.wsscore.domain.library.command;

import co.com.sofka.wsscore.domain.generic.Command;
import co.com.sofka.wsscore.domain.generic.DomainEvent;

import java.time.LocalDate;
import java.util.List;

public class UpdateResourceCommand extends Command {
    private String resourceId;
    private String name;
    private String resourceType;
    private String area;
    private LocalDate date;
    private Boolean available;

    public UpdateResourceCommand(){

    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

}
