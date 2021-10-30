package co.com.sofka.wsscore.infra;


import co.com.sofka.wsscore.domain.library.command.CreateResourceCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        bus.publish(command.getType(), command);//emitir comandos, los casos de uso
        return Response.ok().build();
    }
}