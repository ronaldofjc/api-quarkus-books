package br.com.books.entity;

import java.time.LocalDateTime;

import javax.annotation.ManagedBean;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "books")
public class Book extends PanacheMongoEntity {
  private String title;
  private String author;
  private int pages;
  private boolean active;
  private LocalDateTime creationTime;
  private LocalDateTime updateTime;
}
