package dev.danvega.newsletter;

import dev.danvega.newsletter.model.Subscriber;
import dev.danvega.newsletter.repository.SubscriberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class NewsletterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsletterApplication.class, args);
    }

    @Bean
    @Profile({"dev","test"})
    CommandLineRunner commandLineRunner(SubscriberRepository subscribers) {
        return args -> {
            if(subscribers.count() > 0) {
                subscribers.deleteAll();
            }
            subscribers.save(new Subscriber(null,"danvega@gmail.com"));
            subscribers.save(new Subscriber(null,"dvega@vmware.com"));
            subscribers.save(new Subscriber(null,"dan@danvega.dev"));
        };
    }

}
