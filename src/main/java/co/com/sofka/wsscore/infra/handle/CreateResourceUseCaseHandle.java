package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.library.command.CreateResourceCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.CreateResourceUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateResourceUseCaseHandle extends UseCaseHandle {

    private final CreateResourceUseCase createResourceUseCase;

    public CreateResourceUseCaseHandle(CreateResourceUseCase createResourceUseCase) {
        this.createResourceUseCase = createResourceUseCase;
    }

    @ConsumeEvent(value = "sofkau.resource.createresource")
    void consumeBlocking(CreateResourceCommand command) {
        var events = createResourceUseCase.apply(command);
        saveResource(command.getResourceId(), events);
    }
}