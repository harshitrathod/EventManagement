package com.harshit.service;


import com.harshit.domain.Greeting;

import java.util.List;

public interface GreetingService {
  List<Greeting> getAll();
  Greeting add(Greeting greeting);
}
