package com.gdipaolantonio.ripostiglio.domain;

public class ItemBuilder {

  private String name = "Default name from Builder";

  private ItemBuilder() {}

  public static ItemBuilder anItem() {
    return new ItemBuilder();
  }

  public Item build() {
    return new Item(name);
  }

  public ItemBuilder withName(String name) {
    this.name = name;
    return this;
  }
}
