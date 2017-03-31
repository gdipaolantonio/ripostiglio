package com.gdipaolantonio.ripostiglio.domain;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageWriteModel {

  private final EventFactory factory;
  private final EventStore eventStore;

  public StorageWriteModel(EventFactory factory, EventStore eventStore) {
    this.factory = factory;
    this.eventStore = eventStore;
  }

  public void addItem(Item item) {
    eventStore.store(factory.newAddItemEvent(item));
  }

  public StorageStatus status() {
    return StorageStatus.of(eventStore.events());
  }
}
