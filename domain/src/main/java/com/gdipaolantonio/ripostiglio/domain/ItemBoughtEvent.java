package com.gdipaolantonio.ripostiglio.domain;

import java.time.Instant;

public class ItemBoughtEvent implements Event<Item> {

  private final Instant created;
  private final Item item;

  public ItemBoughtEvent(Instant created, Item item) {
    this.created = created;
    this.item = item;
  }

  @Override
  public Instant created() {
    return created;
  }

  @Override
  public Item body() {
    return item;
  }
}
