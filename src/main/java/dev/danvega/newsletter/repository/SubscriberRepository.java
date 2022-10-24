package dev.danvega.sfcnewsletter.repository;

import dev.danvega.sfcnewsletter.model.Subscriber;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SubscriberRepository extends Repository<Subscriber,Integer> {

    List<Subscriber> findAll();

}
