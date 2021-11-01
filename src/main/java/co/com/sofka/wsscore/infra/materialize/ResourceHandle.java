package co.com.sofka.wsscore.infra.materialize;

import co.com.sofka.wsscore.domain.library.event.ResourceCreated;
import co.com.sofka.wsscore.domain.library.event.ResourceUpdated;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class ResourceHandle {
    private final MongoClient mongoClient;

    public ResourceHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofkau.library.resourcecreated", blocking = true)
    void consumeResourceCreated(ResourceCreated event) {
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

    @ConsumeEvent(value = "sofkau.library.resourceupdated", blocking = true)
    void consumeResourceUpdated(ResourceUpdated event)
    {
        System.out.println("Event detected in consumer");
        Map<String, Object> document = new HashMap<>();
        document.put("name", event.getName());
        document.put("resourceType", event.getResourceType());
        document.put("area", event.getArea());
        document.put("date", event.getDate());
        document.put("available", event.getAvailable());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("resource")
                .updateOne(Filters.eq("_id", event.getAggregateId()), updateObject);
    }
}