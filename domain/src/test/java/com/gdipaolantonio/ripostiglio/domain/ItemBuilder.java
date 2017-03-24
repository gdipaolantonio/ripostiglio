package com.gdipaolantonio.ripostiglio.domain;

public class ItemBuilder {

  private ItemKey key = new ItemKey("Default name from Builder");

  private ItemBuilder() {}

  public static ItemBuilder anItem() {
    return new ItemBuilder();
  }

  public Item build() {
    return new Item(key);
  }

  public ItemBuilder withName(String name) {
    this.key = new ItemKey(name);
    return this;
  }
}
