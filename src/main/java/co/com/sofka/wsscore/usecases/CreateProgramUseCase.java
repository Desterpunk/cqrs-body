package co.com.sofka.wsscore.usecases;

import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.library.Resource;
import co.com.sofka.wsscore.domain.library.command.CreateResourceCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreateProgramUseCase  implements Function<CreateResourceCommand, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(CreateResourceCommand command) {
        var program = new Resource(command.getResourceId(), command.getName(),command.getResourceType(),command.getArea(),command.getDate(),command.getAvailable());
        return program.getUncommittedChanges();
    }
}
