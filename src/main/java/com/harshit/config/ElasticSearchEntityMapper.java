/*
 *  *************************************************************************|
 *   Copyright (c) 2016. Yaana Technologies. All rights reserved.           *|
 *   Yaana Technologies PROPRIETARY/CONFIDENTIAL                            *|
 *  *************************************************************************|
 */
package com.harshit.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.EntityMapper;

import java.io.IOException;

public class ElasticSearchEntityMapper implements EntityMapper {
  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchEntityMapper.class);
  private ObjectMapper objectMapper;

  public ElasticSearchEntityMapper() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Override
  public String mapToString(Object object) throws IOException {
    final String value = objectMapper.writeValueAsString(object);
    if(LOGGER.isDebugEnabled()) {
      LOGGER.debug("Entity Mapper Json String: " + value);
    }
    return value;
  }

  @Override
  public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
    return objectMapper.readValue(source, clazz);
  }
}
