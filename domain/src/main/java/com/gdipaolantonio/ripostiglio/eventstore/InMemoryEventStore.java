package com.gdipaolantonio.ripostiglio.eventstore;

import java.util.Collection;

import com.gdipaolantonio.ripostiglio.domain.Event;

public class InMemoryEventStore implements EventStore {

  private final Collection<Event<?>> events;

  public InMemoryEventStore(Collection<Event<?>> events) {
    this.events = events;
  }

  @Override
  public void store(Event<?> event) {
    events.add(event);
  }
}
