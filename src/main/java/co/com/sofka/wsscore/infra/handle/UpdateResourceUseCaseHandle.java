package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.library.command.UpdateResourceCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.UpdateResourceUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UpdateResourceUseCaseHandle extends UseCaseHandle {
    private final UpdateResourceUseCase updateResourceUseCase;

    public UpdateResourceUseCaseHandle(UpdateResourceUseCase updateResourceUseCase)
    {
        this.updateResourceUseCase = updateResourceUseCase;
    }
    @ConsumeEvent(value = "sofkau.library.updateresource")
    public void updateResourceConsumer(UpdateResourceCommand updateResourceCommand)
    {
        var events = updateResourceUseCase.apply(updateResourceCommand);
        saveResource(updateResourceCommand.getResourceId(), events);
    }
}
