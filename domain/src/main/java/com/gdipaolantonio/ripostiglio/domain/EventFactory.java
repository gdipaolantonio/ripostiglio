package com.gdipaolantonio.ripostiglio.domain;

public interface EventFactory {

  Event<Item> newItemAddedEvent(Item item);
}
