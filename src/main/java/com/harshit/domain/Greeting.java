package com.harshit.domain;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "greeting_v1")
public class Greeting {

  @Field(type = FieldType.String)
  private String content;

  @Field(type = FieldType.Long)
  private Long id;

  public void setContent(String content) {
    this.content = content;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public Long getId() {
    return id;
  }
}
