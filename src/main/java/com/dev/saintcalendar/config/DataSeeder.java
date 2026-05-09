package com.dev.saintcalendar.config;

import com.dev.saintcalendar.model.Quote;
import com.dev.saintcalendar.model.Relic;
import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.repository.SaintRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(SaintRepository repository) {
        return args -> {
            // Only load the data if the table is currently empty.
            // This prevents duplicate entries every time you restart the Docker container.
            if (repository.count() == 0) {
                ObjectMapper mapper = new ObjectMapper();
                // TypeReference tells Jackson we are expecting a List of Saint objects
                TypeReference<List<Saint>> typeReference = new TypeReference<List<Saint>>(){};
                
                // Read the file from the resources folder
                InputStream inputStream = TypeReference.class.getResourceAsStream("/saints-init.json");
                // api-1  | Unable to save initial data: argument "src" is null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                try {
                    List<Saint> saints = mapper.readValue(inputStream, typeReference);
                    for (Saint saint : saints) {

                        if (saint.getQuotes() != null) {
                            for (Quote quote : saint.getQuotes()) {
                                quote.setSaint(saint); 
                            }
                        }
                        
                        // If the saint has relics, tell each relic who its parent saint is
                        if (saint.getRelics() != null) {
                            for (Relic relic : saint.getRelics()) {
                                relic.setSaint(saint);
                            }
                        }
                    }
                    repository.saveAll(saints);
                    System.out.println("Successfully seeded database with initial Saints!");
                } catch (Exception e) {
                    System.out.println("Unable to save initial data: " + e.getMessage());
                }
            } else {
                System.out.println("Database already contains data. Skipping seeding.");
            }
        };
    }
}
