package dev.danvega.newsletter.repository;

import dev.danvega.newsletter.model.Subscriber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriberRepository extends CrudRepository<Subscriber,Integer> {

    List<Subscriber> findAll();

}
