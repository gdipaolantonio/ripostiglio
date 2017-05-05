package com.gdipaolantonio.ripostiglio.domain;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageQuery {

  private final EventStore eventStore;

  public StorageQuery(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  public long quantityFor(ItemKey key) {
    return storage().quantityFor(key);
  }

  private Storage storage() {
    return eventStore.events()
        .reduce(new Storage(), Storage::apply, (t, u) -> null);
  }
}
