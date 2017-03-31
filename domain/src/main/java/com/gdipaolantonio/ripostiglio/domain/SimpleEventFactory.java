package com.gdipaolantonio.ripostiglio.domain;

import java.time.Clock;

public class SimpleEventFactory implements EventFactory {

  private final Clock clock;

  public SimpleEventFactory(Clock clock) {
    this.clock = clock;
  }

  @Override
  public Event<Item> newItemAddedEvent(Item item) {
    return new ItemAddedEvent(clock.instant(), item);
  }
}
