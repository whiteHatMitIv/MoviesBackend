package com.example.movies;

import com.example.movies.models.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DatabaseInitializr implements CommandLineRunner {
    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper;

    public DatabaseInitializr(MongoTemplate mongoTemplate, ObjectMapper objectMapper) {
        this.mongoTemplate = mongoTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🔄 Vérification et initialisation de la base de données...");

        loadCollection("data/movie-api-db.movies.json", "movies", Movie.class);

        System.out.println("✅ Vérification terminée !");
    }

    private <T> void loadCollection(String filePath, String collectionName, Class<T> clazz) throws Exception {
        if (mongoTemplate.count(new Query(), collectionName) == 0) {
            InputStream inputStream = new ClassPathResource(filePath).getInputStream();
            List<T> data = objectMapper.readValue(inputStream, new TypeReference<>() {});

            if (!data.isEmpty()) {
                mongoTemplate.insert(data, collectionName);
                System.out.println("📥 Données insérées dans la collection : " + collectionName);
            } else {
                System.out.println("⚠️ Aucune donnée à insérer pour " + collectionName);
            }
        } else {
            System.out.println("✅ La collection " + collectionName + " contient déjà des données, pas d'insertion.");
        }
    }
}
