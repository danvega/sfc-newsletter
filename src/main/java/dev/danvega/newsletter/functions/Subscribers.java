package dev.danvega.newsletter.functions;

import dev.danvega.newsletter.model.Subscriber;
import dev.danvega.newsletter.repository.SubscriberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.function.context.MessageRoutingCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Configuration
public class Subscribers {

    private static final Logger LOG = LoggerFactory.getLogger(Subscribers.class);
    private final SubscriberRepository subscribers;

    public Subscribers(SubscriberRepository subscribers) {
        this.subscribers = subscribers;
    }

    @Bean
    public MessageRoutingCallback customRouter() {
        return new MessageRoutingCallback() {
            @Override
            public FunctionRoutingResult routingResult(Message<?> message) {
                return (FunctionRoutingResult) message.getHeaders().get("func_name");
            }
        };
    }

    @Bean
    public Supplier<List<String>> findAll() {
        LOG.info("findAll Subscribers");
        return() -> subscribers.findAll()
                .stream()
                .map(subscriber -> subscriber.getEmail())
                .collect(Collectors.toList());
    }

    @Bean
    public Consumer<String> create() {
        LOG.info("create new subscriber");
        return (email) -> {
            Subscriber subscriber = new Subscriber(null,email);
            subscribers.save(subscriber);
        };
    }

}
