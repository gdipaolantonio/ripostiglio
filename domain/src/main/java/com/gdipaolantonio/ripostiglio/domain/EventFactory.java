package com.gdipaolantonio.ripostiglio.domain;

public interface EventFactory {

  Event<Item> newAddItemEvent(Item item);
}
