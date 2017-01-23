package com.harshit.rest;

import com.harshit.domain.Greeting;
import com.harshit.repo.GreetingRepository;
import com.harshit.service.GreetingService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.elasticsearch.common.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private final AtomicLong counter = new AtomicLong();

  @Autowired
  private GreetingService greetingService;

  @RequestMapping(value = "/greeting", method = RequestMethod.POST)
  public Greeting createGreeting(@RequestParam(value="name", defaultValue="World") String name) {
    Greeting greeting = new Greeting();
    greeting.setId(counter.addAndGet(1));
    greeting.setContent(name);
    return greetingService.add(greeting);
  }

  @RequestMapping(value = "/greeting", method = RequestMethod.GET)
  public ResponseEntity<List<Greeting>> getAllGreeting() {
    return new ResponseEntity<>(greetingService.getAll(), HttpStatus.OK);
  }
}
