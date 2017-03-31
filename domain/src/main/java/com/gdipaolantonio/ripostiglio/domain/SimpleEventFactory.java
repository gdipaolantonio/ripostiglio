package com.gdipaolantonio.ripostiglio.domain;

import java.time.Clock;

public class SimpleEventFactory implements EventFactory {

  private final Clock clock;

  public SimpleEventFactory(Clock clock) {
    this.clock = clock;
  }

  @Override
  public Event<Item> newItemBoughtEvent(Item item) {
    return new ItemBoughtEvent(clock.instant(), item);
  }
}
