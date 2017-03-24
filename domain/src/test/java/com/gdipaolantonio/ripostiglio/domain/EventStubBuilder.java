package com.gdipaolantonio.ripostiglio.domain;

import java.time.Instant;

public class EventStubBuilder<Body> {

  private Instant created = Instant.now();
  private Body body = (Body) "Default for EventStubBuilder";

  private EventStubBuilder() {}

  public static <Body> EventStubBuilder<Body> anEventStub() {
    return new EventStubBuilder<Body>();
  }

  public EventStub<Body> build() {
    return new EventStub<Body>(created, body);
  }
}
