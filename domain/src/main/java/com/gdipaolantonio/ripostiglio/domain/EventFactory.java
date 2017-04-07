package com.gdipaolantonio.ripostiglio.domain;

public interface EventFactory {

  Event<Item> newItemStoredEvent(Item item);
  Event<Item> newItemEvictedEvent(Item item);
}
