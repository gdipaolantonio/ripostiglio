package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.time.Instant.EPOCH;

import java.time.Instant;

public class ItemAddedEventBuilder {

  private Instant instant = EPOCH;
  private Item item = anItem().build();;

  private ItemAddedEventBuilder() {}

  public static ItemAddedEventBuilder anItemAddedEvent() {
    return new ItemAddedEventBuilder();
  }

  public Event<Item> build() {
    return new ItemAddedEvent(instant, item);
  }

  public ItemAddedEventBuilder of(ItemBuilder item) {
    this.item = item.build();
    return this;
  }
}
