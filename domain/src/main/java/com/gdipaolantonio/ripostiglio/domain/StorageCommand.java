package com.gdipaolantonio.ripostiglio.domain;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageCommand {

  private final EventFactory factory;
  private final EventStore eventStore;

  public StorageCommand(EventFactory factory, EventStore eventStore) {
    this.factory = factory;
    this.eventStore = eventStore;
  }

  public void store(Item item) {
    eventStore.append(factory.newItemStoredEvent(item));
  }

  public void evict(Item item) {
    eventStore.append(factory.newItemEvictedEvent(item));
  }
}
