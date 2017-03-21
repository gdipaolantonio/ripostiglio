package com.gdipaolantonio.ripostiglio.domain;

public class ItemBuilder {

  private ItemBuilder() {}

  public static ItemBuilder anItem() {
    return new ItemBuilder();
  }

  public Item build() {
    return new Item();
  }
}
