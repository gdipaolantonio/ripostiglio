package com.gdipaolantonio.ripostiglio.domain;

public class Storage {

  private final EventFactory factory;
  private final EventStore eventStore;

  public Storage(EventFactory factory, EventStore eventStore) {
    this.factory = factory;
    this.eventStore = eventStore;
  }

  public void addItem(Item item) {
    eventStore.store(factory.newAddItemEvent(item));
  }
}