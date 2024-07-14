package com.service.note;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.note.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
        createCollectionIfNotExists(mongoTemplate);
        if (isDataEmpty(mongoTemplate)) {
            loadData(mongoTemplate);
        }
    }

    private void createCollectionIfNotExists(MongoTemplate mongoTemplate) throws InterruptedException {
        boolean collectionExists = mongoTemplate.collectionExists("note");
        if (!collectionExists) {
            mongoTemplate.createCollection("note");
            System.out.println("Created collection 'note'");
        }
    }

    private boolean isDataEmpty(MongoTemplate mongoTemplate) {
        return mongoTemplate.count(new Query(), Note.class) == 0;
    }

    private void loadData(MongoTemplate mongoTemplate) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Note>> typeReference = new TypeReference<List<Note>>() {};
        InputStream inputStream = new ClassPathResource("data.json").getInputStream();
        List<Note> notes = mapper.readValue(inputStream, typeReference);
        mongoTemplate.insertAll(notes);
        System.out.println("Loaded initial data into MongoDB");
    }
}
