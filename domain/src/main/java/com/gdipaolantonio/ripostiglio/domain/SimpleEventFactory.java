package com.gdipaolantonio.ripostiglio.domain;

import java.time.Clock;

public class SimpleEventFactory implements EventFactory {

  private final Clock clock;

  public SimpleEventFactory(Clock clock) {
    this.clock = clock;
  }

  @Override
  public Event<Item> newItemStoredEvent(Item item) {
    return new ItemStoredEvent(clock.instant(), item);
  }
}
