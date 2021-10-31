package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.library.command.CreateResourceCommand;
import co.com.sofka.wsscore.domain.library.command.DeleteResourceCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.CreateResourceUseCase;
import co.com.sofka.wsscore.usecases.DeleteResourceUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteResourceUseCaseHandle extends UseCaseHandle {

    private final DeleteResourceUseCase deleteResourceUseCase;

    public DeleteResourceUseCaseHandle(DeleteResourceUseCase deleteResourceUseCase) {
        this.deleteResourceUseCase = deleteResourceUseCase;
    }

    @ConsumeEvent(value = "sofkau.resource.deleteresource")
    void consumeBlocking(DeleteResourceCommand command) {
        var events = deleteResourceUseCase.apply(command);
        saveResource(command.getResourceId(), events);
    }
}