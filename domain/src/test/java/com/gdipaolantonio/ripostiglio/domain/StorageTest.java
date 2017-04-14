package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static com.gdipaolantonio.ripostiglio.domain.ItemEvictedEventBuilder.anItemEvictedEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemStoredEventBuilder.anItemStoredEvent;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StorageTest {

  @Test
  public void emptyStorage() throws Exception {

    Storage storage = new Storage();

    assertThat(storage.quantityFor(new ItemKey("smartphone")), is(0L));
    assertThat(storage.quantityFor(new ItemKey("tablet")), is(0L));
    assertThat(storage.quantityFor(new ItemKey("laptop")), is(0L));
    assertThat(storage.quantityFor(new ItemKey("any")), is(0L));
  }

  @Test
  public void storeOneTypeOfItemJustOneTime() throws Exception {

    Storage storage = new Storage().apply(oneSmartphoneStored());

    assertThat(storage.quantityFor(new ItemKey("smartphone")), is(1L));
  }

  @Test
  public void storeOneTypeOfItemManyTimes() throws Exception {

    Storage storage = new Storage()
      .apply(oneSmartphoneStored())
      .apply(oneSmartphoneStored())
      .apply(oneSmartphoneStored());

    assertThat(storage.quantityFor(new ItemKey("smartphone")), is(3L));
  }

  @Test
  public void storeManyTypesOfItemManyTimes() throws Exception {

    Storage storage = new Storage()
      .apply(oneSmartphoneStored())
      .apply(oneSmartphoneStored())
      .apply(oneSmartphoneStored())
      .apply(oneTabletStored())
      .apply(oneTabletStored())
      .apply(oneLaptopStored());

    assertThat(storage.quantityFor(new ItemKey("smartphone")), is(3L));
    assertThat(storage.quantityFor(new ItemKey("tablet")), is(2L));
    assertThat(storage.quantityFor(new ItemKey("laptop")), is(1L));
  }

  @Test
  public void evictItems() throws Exception {

    Storage storage = new Storage()
      .apply(oneSmartphoneStored())
      .apply(oneSmartphoneStored())
      .apply(oneSmartphoneStored())
      .apply(oneTabletStored())
      .apply(oneTabletStored())
      .apply(oneLaptopStored())
      .apply(oneSmartphoneEvicted())
      .apply(oneTabletEvicted())
      ;

    assertThat(storage.quantityFor(new ItemKey("smartphone")), is(2L));
    assertThat(storage.quantityFor(new ItemKey("tablet")), is(1L));
    assertThat(storage.quantityFor(new ItemKey("laptop")), is(1L));
  }

  private Event<Item> oneSmartphoneStored() {
    return anItemStoredEvent().of(anItem().withName("smartphone")).build();
  }

  private Event<Item> oneSmartphoneEvicted() {
    return anItemEvictedEvent().of(anItem().withName("smartphone")).build();
  }

  private Event<Item> oneTabletStored() {
    return anItemStoredEvent().of(anItem().withName("tablet")).build();
  }

  private Event<Item> oneTabletEvicted() {
    return anItemEvictedEvent().of(anItem().withName("tablet")).build();
  }

  private Event<Item> oneLaptopStored() {
    return anItemStoredEvent().of(anItem().withName("laptop")).build();
  }
}
