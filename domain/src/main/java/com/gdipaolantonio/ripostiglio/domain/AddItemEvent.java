package com.gdipaolantonio.ripostiglio.domain;

import java.time.Instant;

public class AddItemEvent {

  private final Instant created;
  private final Item item;

  public AddItemEvent(Instant created, Item item) {
    this.created = created;
    this.item = item;
  }

  public Instant created() {
    return created;
  }

  public Item body() {
    return item;
  }
}
