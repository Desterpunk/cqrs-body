package co.com.sofka.wsscore.infra.materialize;

import co.com.sofka.wsscore.domain.library.event.ResourceCreated;
import com.mongodb.client.MongoClient;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class ProgramHandle {
    private final MongoClient mongoClient;

    public ProgramHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofkau.program.resourcecreated", blocking = true)
    void consumeProgramCreated(ResourceCreated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getName());
        document.put("resourceType", event.getResourceType());
        document.put("area", event.getArea());
        document.put("date", event.getDate());
        document.put("available", event.getAvailable());

        mongoClient.getDatabase("queries")
                .getCollection("resource")
                .insertOne(new Document(document));
    }
}