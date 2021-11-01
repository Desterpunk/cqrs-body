package co.com.sofka.wsscore.infra;


import co.com.sofka.wsscore.domain.library.command.CreateResourceCommand;
import co.com.sofka.wsscore.domain.library.command.UpdateResourceCommand;
import co.com.sofka.wsscore.domain.library.command.DeleteResourceCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/api")
public class CommandController {

    private final EventBus bus;
    public CommandController(EventBus bus){
        this.bus = bus;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createResource")
    public Response executor(CreateResourceCommand command) {
        command.setDate(LocalDate.now());
        bus.publish(command.getType(), command);//emitir comandos, los casos de uso
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateResource")
    public Response executorUpdateResource(UpdateResourceCommand command) {
        command.setDate(LocalDate.now());
        bus.publish(command.getType(), command);//emitir comandos, los casos de uso
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteResource")
    public Response executor(DeleteResourceCommand command){
        bus.publish(command.getType(),command);
        return Response.ok().build();
    }

}