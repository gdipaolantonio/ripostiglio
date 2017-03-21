package com.gdipaolantonio.ripostiglio.domain;

public class AddItemEventBuilder {

  private AddItemEventBuilder() {}

  public static AddItemEventBuilder anAddItemEvent() {
    return new AddItemEventBuilder();
  }

  public AddItemEvent build() {
    return new AddItemEvent();
  }
}
