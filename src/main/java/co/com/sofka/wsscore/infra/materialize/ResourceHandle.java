package co.com.sofka.wsscore.infra.materialize;

import co.com.sofka.wsscore.domain.library.event.ResourceCreated;
import com.mongodb.client.MongoClient;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class ResourceHandle {
    private final MongoClient mongoClient;

    public ResourceHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofkau.resource.resourcecreated", blocking = true)
    void consumeResourceCreated(ResourceCreated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getName());

        mongoClient.getDatabase("queries")
                .getCollection("resource")
                .insertOne(new Document(document));
    }
}