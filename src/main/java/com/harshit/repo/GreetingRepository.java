package com.harshit.repo;

import com.harshit.domain.Greeting;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface GreetingRepository extends ElasticsearchRepository<Greeting, Long> {
  List<Greeting> findAll();
}
