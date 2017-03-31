package com.gdipaolantonio.ripostiglio.domain;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageCommandModel {

  private final EventFactory factory;
  private final EventStore eventStore;

  public StorageCommandModel(EventFactory factory, EventStore eventStore) {
    this.factory = factory;
    this.eventStore = eventStore;
  }

  public void addItem(Item item) {
    eventStore.store(factory.newItemAddedEvent(item));
  }
}
