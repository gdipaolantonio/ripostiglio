package com.gdipaolantonio.ripostiglio.domain;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageQuery {

  private final EventStore eventStore;

  public StorageQuery(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  public long quantityFor(ItemKey key) {
    Storage storage = eventStore.events().reduce(new Storage(), Storage::apply, (t, u) -> null);
    return storage.quantityFor(key);
  }
}
