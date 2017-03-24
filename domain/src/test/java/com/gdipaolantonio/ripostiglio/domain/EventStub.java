package com.gdipaolantonio.ripostiglio.domain;

import java.time.Instant;
import java.util.Objects;

public class EventStub<Body> implements Event<Body> {

  private final Instant created;
  private final Body body;

  public EventStub(Instant created, Body body) {
    this.created = created;
    this.body = body;
  }

  @Override
  public Instant created() {
    return created;
  }

  @Override
  public Body body() {
    return body;
  }

  @Override
  public int hashCode() {
    return Objects.hash(created, body);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    EventStub<?> other = (EventStub<?>) obj;
    return Objects.equals(created, other.created)
        && Objects.equals(body, other.body);
  }

  @Override
  public String toString() {
    return "EventStub [created=" + created + ", body=" + body + "]";
  }
}
