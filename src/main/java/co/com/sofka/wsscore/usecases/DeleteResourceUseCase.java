package co.com.sofka.wsscore.usecases;

import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventStoreRepository;
import co.com.sofka.wsscore.domain.library.Resource;
import co.com.sofka.wsscore.domain.library.command.DeleteResourceCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class DeleteResourceUseCase implements Function<DeleteResourceCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public DeleteResourceUseCase(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(DeleteResourceCommand command) {
        var resource = Resource.from(
                command.getResourceId(), repository.getEventsBy("resource",command.getResourceId())
        );
        resource.delete();
        return resource.getUncommittedChanges();
    }
}