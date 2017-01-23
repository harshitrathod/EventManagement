/*
 *  *************************************************************************|
 *   Copyright (c) 2016. Yaana Technologies. All rights reserved.           *|
 *   Yaana Technologies PROPRIETARY/CONFIDENTIAL                            *|
 *  *************************************************************************|
 */
package com.harshit.config;


import com.harshit.util.AutowireBean;

import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@AutoConfigureAfter(ElasticsearchAutoConfiguration.class)
@EnableElasticsearchRepositories(basePackages="com.yt.rms.api.search.elastic")
public class ElasticSearchConfiguration {
  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

  @Bean
  public ElasticsearchTemplate elasticsearchTemplate(Client client) {
    try {
      return new ElasticsearchTemplate(client, mapper());
    }
    catch (Exception ex) {
      throw new IllegalStateException(ex);
    }
  }

  private EntityMapper mapper(){
    return  new ElasticSearchEntityMapper();
  }

  @Bean
  public AutowireBean autowireBean() {
    return AutowireBean.getInstance();
  }

}
