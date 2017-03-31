package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.time.Instant.EPOCH;

import java.time.Instant;

public class ItemBoughtEventBuilder {

  private Instant instant = EPOCH;
  private ItemBuilder item = anItem();

  private ItemBoughtEventBuilder() {}

  public static ItemBoughtEventBuilder anItemBoughtEvent() {
    return new ItemBoughtEventBuilder();
  }

  public Event<Item> build() {
    return new ItemBoughtEvent(instant, item.build());
  }

  public ItemBoughtEventBuilder of(ItemBuilder item) {
    this.item = item;
    return this;
  }
}
