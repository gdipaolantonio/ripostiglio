package com.gdipaolantonio.ripostiglio.domain;

import java.time.Instant;

public class AddItemEvent implements Event<Item> {

  private final Instant created;
  private final Item item;

  public AddItemEvent(Instant created, Item item) {
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
