package com.gdipaolantonio.ripostiglio.domain;

public interface EventStore {

  void store(AddItemEvent event);
}
