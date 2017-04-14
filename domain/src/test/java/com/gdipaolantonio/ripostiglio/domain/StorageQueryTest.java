package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static com.gdipaolantonio.ripostiglio.domain.ItemEvictedEventBuilder.anItemEvictedEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemStoredEventBuilder.anItemStoredEvent;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.gdipaolantonio.ripostiglio.eventstore.InMemoryEventStore;

public class StorageQueryTest {

  @Test
  public void withAnEmptyStream() throws Exception {

    StorageQuery storage = new StorageQuery(eventStoreWith(emptyList()));

    long count = storage.quantityFor(new ItemKey("item name"));

    assertThat(count, is(0L));
  }

  @Test
  public void storeOneTypeOfItem() throws Exception {

    List<Event<?>> events = asList(
      oneSmartphoneStored(),
      oneSmartphoneStored(),
      oneSmartphoneStored()
    );
    StorageQuery storage = new StorageQuery(eventStoreWith(events));

    long count = storage.quantityFor(new ItemKey("smartphone"));

    assertThat(count, is(3L));
  }

  @Test
  public void storeManyItemsOfDifferentTypes() throws Exception {

    List<Event<?>> events = asList(
      oneSmartphoneStored(),
      oneSmartphoneStored(),
      oneSmartphoneStored(),
      oneLaptopStored(),
      oneTabletStored(),
      oneTabletStored()
    );
    StorageQuery status = new StorageQuery(eventStoreWith(events));

    long smartphoneCount = status.quantityFor(new ItemKey("smartphone"));
    long tabletCount = status.quantityFor(new ItemKey("tablet"));
    long laptopCount = status.quantityFor(new ItemKey("laptop"));

    assertThat(smartphoneCount, is(3L));
    assertThat(tabletCount, is(2L));
    assertThat(laptopCount, is(1L));
  }

  @Test
  public void storeAndEvictOneTypeOfItem() throws Exception {

    List<Event<?>> events = asList(
      oneSmartphoneStored(),
      oneSmartphoneStored(),
      oneSmartphoneStored(),
      oneSmartphoneEvicted()
    );
    StorageQuery storage = new StorageQuery(eventStoreWith(events));

    long count = storage.quantityFor(new ItemKey("smartphone"));

    assertThat(count, is(2L));
  }

  private InMemoryEventStore eventStoreWith(List<Event<?>> events) {
    return new InMemoryEventStore(events);
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

  private Event<Item> oneLaptopStored() {
    return anItemStoredEvent().of(anItem().withName("laptop")).build();
  }
}
