package com.gdipaolantonio.ripostiglio.eventstore;

import java.util.Collection;
import java.util.stream.Stream;

import com.gdipaolantonio.ripostiglio.domain.Event;

public class InMemoryEventStore implements EventStore {

  private final Collection<Event<?>> events;

  public InMemoryEventStore(Collection<Event<?>> events) {
    this.events = events;
  }

  @Override
  public void append(Event<?> event) {
    events.add(event);
  }

  @Override
  public Stream<Event<?>> events() {
    return events.stream();
  }
}
