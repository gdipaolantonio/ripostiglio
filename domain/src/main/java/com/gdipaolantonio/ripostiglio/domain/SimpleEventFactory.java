package com.gdipaolantonio.ripostiglio.domain;

import java.time.Clock;

public class SimpleEventFactory implements EventFactory {

  private final Clock clock;

  public SimpleEventFactory(Clock clock) {
    this.clock = clock;
  }

  @Override
  public Event<Item> newAddItemEvent(Item item) {
    return new AddItemEvent(clock.instant(), item);
  }
}
