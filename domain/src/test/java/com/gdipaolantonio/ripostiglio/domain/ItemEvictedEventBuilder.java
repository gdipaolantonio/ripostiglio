package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.time.Instant.EPOCH;

import java.time.Instant;

public class ItemEvictedEventBuilder {

  private Instant created = EPOCH;
  private Item item = anItem().build();

  private ItemEvictedEventBuilder() {}

  public static ItemEvictedEventBuilder anItemEvictedEvent() {
    return new ItemEvictedEventBuilder();
  }

  public ItemEvictedEvent build() {
    return new ItemEvictedEvent(created, item);
  }

  public ItemEvictedEventBuilder of(ItemBuilder item) {
    this.item = item.build();
    return this;
  }
}
