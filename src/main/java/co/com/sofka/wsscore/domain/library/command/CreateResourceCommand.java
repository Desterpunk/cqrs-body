package co.com.sofka.wsscore.domain.library.command;

import co.com.sofka.wsscore.domain.generic.Command;

public class CreateResourceCommand extends Command {
    private String resourceId;
    private String name;

    public CreateResourceCommand(){

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
}
