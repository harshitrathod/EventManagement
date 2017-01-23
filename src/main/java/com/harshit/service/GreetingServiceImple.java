package com.harshit.service;

import com.harshit.domain.Greeting;
import com.harshit.repo.GreetingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Service
public class GreetingServiceImple implements GreetingService {


  @Autowired
  private GreetingRepository greetingRepository;

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;
  @Override
  public List<Greeting> getAll() {
    return elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().withQuery(boolQuery()).build
            (),Greeting.class);
  }

  @Override
  public Greeting add(Greeting greeting) {
    return greetingRepository.index(greeting);
  }
}
