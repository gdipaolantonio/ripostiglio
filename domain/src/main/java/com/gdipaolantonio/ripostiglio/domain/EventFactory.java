package com.gdipaolantonio.ripostiglio.domain;

public interface EventFactory {

  Event<Item> newItemBoughtEvent(Item item);
}
