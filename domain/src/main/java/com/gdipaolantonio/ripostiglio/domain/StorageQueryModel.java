package com.gdipaolantonio.ripostiglio.domain;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageQueryModel {

  private final EventStore eventStore;

  public StorageQueryModel(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  public long quantityFor(ItemKey itemKey) {
    return eventStore.events()
      .filter(event -> event instanceof ItemAddedEvent)
      .map(event -> (ItemAddedEvent) event)
      .filter(event -> event.body().hasKey(itemKey))
      .count();
  }
}
