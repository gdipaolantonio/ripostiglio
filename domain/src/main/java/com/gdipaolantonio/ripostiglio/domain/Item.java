package com.gdipaolantonio.ripostiglio.domain;

import java.util.Objects;

public class Item {

  private final ItemKey key;

  public Item(ItemKey key) {
    this.key = key;
  }

  public boolean hasKey(ItemKey key) {
    return this.key.equals(key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Item other = (Item) obj;

    return Objects.equals(key, other.key);
  }

  @Override
  public String toString() {
    return "Item [key=" + key + "]";
  }
}
