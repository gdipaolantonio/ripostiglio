package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.time.Instant.EPOCH;

import java.time.Instant;

public class AddItemEventBuilder {

  private Instant instant = EPOCH;
  private Item item = anItem().build();;

  private AddItemEventBuilder() {}

  public static AddItemEventBuilder anAddItemEvent() {
    return new AddItemEventBuilder();
  }

  public Event build() {
    return new AddItemEvent(instant, item);
  }
}
