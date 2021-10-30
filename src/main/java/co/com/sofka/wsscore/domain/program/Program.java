package co.com.sofka.wsscore.domain.program;

import co.com.sofka.wsscore.domain.generic.AggregateRoot;
import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventChange;
import co.com.sofka.wsscore.domain.program.event.ProgramCreated;

import java.util.*;


public class Program extends AggregateRoot implements EventChange {
    private String name;

    public Program(String programId, String name){
        super(programId);
        appendChange(new ProgramCreated(name)).apply();
    }

    private Program(String id){
        super(id);
        subscribe(this);
        listener((ProgramCreated event)-> {
          this.name = event.getName();
        });
    }

    public static Program from(String id, List<DomainEvent> events){
        var program = new Program(id);
        events.forEach(program::applyEvent);
        return program;
    }

    public String name() {
        return name;
    }
}