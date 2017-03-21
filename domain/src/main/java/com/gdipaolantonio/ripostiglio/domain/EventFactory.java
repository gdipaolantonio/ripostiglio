package com.gdipaolantonio.ripostiglio.domain;

public interface EventFactory {

  AddItemEvent newAddItemEvent(Item item);
}
