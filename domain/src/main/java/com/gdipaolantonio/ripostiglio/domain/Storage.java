package com.gdipaolantonio.ripostiglio.domain;

import java.util.HashMap;
import java.util.Map;

public class Storage {

  private final Map<ItemKey, Long> items;

  public Storage() {
    items = new HashMap<>();
  }

  private Storage(Map<ItemKey, Long> items) {
    this.items = items;
  }

  public Storage apply(Event<?> event) {
    return new Storage(reactTo(event));
  }

  private Map<ItemKey, Long> reactTo(Event<?> event) {

    if (event instanceof ItemStoredEvent) {
      return apply((ItemStoredEvent) event);
    }
    else if (event instanceof ItemEvictedEvent) {
      return apply((ItemEvictedEvent) event);
    }
    else {
      return this.items;
    }
  }

  private Map<ItemKey, Long> apply(ItemStoredEvent event) {
    ItemKey key = event.body().key();
    return increaseQuantityByOneFor(key);
  }

  private Map<ItemKey, Long> apply(ItemEvictedEvent event) {
    ItemKey key = event.body().key();
    return decreaseQuantityByOneFor(key);
  }

  private Map<ItemKey, Long> increaseQuantityByOneFor(ItemKey key) {
    Map<ItemKey, Long> items = new HashMap<>(this.items);
    items.put(key, quantityFor(key) + 1);
    return items;
  }

  private Map<ItemKey, Long> decreaseQuantityByOneFor(ItemKey key) {
    Map<ItemKey, Long> items = new HashMap<>(this.items);
    items.put(key, quantityFor(key) - 1);
    return items;
  }

  public long quantityFor(ItemKey key) {
    return items.containsKey(key) ? items.get(key) : 0;
  }
}
