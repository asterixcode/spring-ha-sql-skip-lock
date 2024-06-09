package com.asterixcode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Message {

  @Id
  @GeneratedValue
  private UUID id;

  private String text;

  @Column(updatable = false)
  private final LocalDateTime createdAt = LocalDateTime.now();

  protected Message() {
  }

  public Message(String text) {
    this.text = text;
  }

  public UUID getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "Message{" +
      "id=" + id +
      ", text='" + text + '\'' +
      '}';
  }
}
