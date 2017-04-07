package com.gdipaolantonio.ripostiglio.domain;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageQuery {

  private final EventStore eventStore;

  public StorageQuery(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  public long quantityFor(ItemKey itemKey) {
    return eventStore.events()
      .filter(event -> event instanceof ItemStoredEvent)
      .map(event -> (ItemStoredEvent) event)
      .filter(event -> event.body().hasKey(itemKey))
      .count();
  }
}
