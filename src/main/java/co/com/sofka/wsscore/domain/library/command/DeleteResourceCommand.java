package co.com.sofka.wsscore.domain.library.command;

import co.com.sofka.wsscore.domain.generic.Command;

public class DeleteResourceCommand extends Command {
    private String resourceId;

    public DeleteResourceCommand(){

    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
