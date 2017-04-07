package com.gdipaolantonio.ripostiglio.domain;

import java.time.Instant;
import java.util.Objects;

public class ItemStoredEvent implements Event<Item> {

  private final Instant created;
  private final Item item;

  public ItemStoredEvent(Instant created, Item item) {
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

  @Override
  public int hashCode() {
    return Objects.hash(created, item);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ItemStoredEvent other = (ItemStoredEvent) obj;

    return Objects.equals(created, other.created)
        && Objects.equals(item, other.item);
  }

  @Override
  public String toString() {
    return "ItemBoughtEvent [created=" + created + ", item=" + item + "]";
  }
}
