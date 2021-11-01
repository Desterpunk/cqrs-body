package co.com.sofka.wsscore.usecases;

import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventStoreRepository;
import co.com.sofka.wsscore.domain.library.Resource;
import co.com.sofka.wsscore.domain.library.command.UpdateResourceCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Dependent
public class UpdateResourceUseCase implements Function<UpdateResourceCommand,
        List<DomainEvent>> {

    private final EventStoreRepository repository;

    public UpdateResourceUseCase(EventStoreRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(UpdateResourceCommand updateResourceCommand) {
        var events = repository.getEventsBy("resource",
                        updateResourceCommand.getResourceId());

        var resource = Resource.from(updateResourceCommand.getResourceId(), events);

        resource.updateData(updateResourceCommand.getName(), updateResourceCommand.getResourceType(),
                updateResourceCommand.getArea(), updateResourceCommand.getDate(),
                updateResourceCommand.getAvailable());

        return resource.getUncommittedChanges();
    }
}
