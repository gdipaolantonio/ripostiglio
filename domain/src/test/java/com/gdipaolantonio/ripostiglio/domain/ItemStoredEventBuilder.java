package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.time.Instant.EPOCH;

import java.time.Instant;

public class ItemStoredEventBuilder {

  private Instant instant = EPOCH;
  private ItemBuilder item = anItem();

  private ItemStoredEventBuilder() {}

  public static ItemStoredEventBuilder anItemStoredEvent() {
    return new ItemStoredEventBuilder();
  }

  public Event<Item> build() {
    return new ItemStoredEvent(instant, item.build());
  }

  public ItemStoredEventBuilder of(ItemBuilder item) {
    this.item = item;
    return this;
  }
}
