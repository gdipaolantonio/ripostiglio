package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBoughtEventBuilder.anItemBoughtEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.gdipaolantonio.ripostiglio.eventstore.InMemoryEventStore;

public class StorageQueryModelTest {

  @Test
  public void withAnEmptyStream() throws Exception {

    StorageQueryModel storage = new StorageQueryModel(eventStoreWith(emptyList()));

    long count = storage.quantityFor(new ItemKey("item name"));

    assertThat(count, is(0L));
  }

  @Test
  public void buyOneTypeOfItem() throws Exception {

    List<Event<?>> events = asList(
      oneSmartphoneBought(),
      oneSmartphoneBought(),
      oneSmartphoneBought()
    );
    StorageQueryModel storage = new StorageQueryModel(eventStoreWith(events));

    long count = storage.quantityFor(new ItemKey("smartphone"));

    assertThat(count, is(3L));
  }

  @Test
  public void buyManyItemsOfDifferentTypes() throws Exception {

    List<Event<?>> events = asList(
      oneSmartphoneBought(),
      oneSmartphoneBought(),
      oneSmartphoneBought(),
      oneLaptopBought(),
      oneTabletBought(),
      oneTabletBought()
    );
    StorageQueryModel status = new StorageQueryModel(eventStoreWith(events));

    long smartphoneCount = status.quantityFor(new ItemKey("smartphone"));
    long tabletCount = status.quantityFor(new ItemKey("tablet"));
    long laptopCount = status.quantityFor(new ItemKey("laptop"));

    assertThat(smartphoneCount, is(3L));
    assertThat(tabletCount, is(2L));
    assertThat(laptopCount, is(1L));
  }

  private InMemoryEventStore eventStoreWith(List<Event<?>> events) {
    return new InMemoryEventStore(events);
  }

  private Event<Item> oneSmartphoneBought() {
    return anItemBoughtEvent().of(anItem().withName("smartphone")).build();
  }

  private Event<Item> oneTabletBought() {
    return anItemBoughtEvent().of(anItem().withName("tablet")).build();
  }

  private Event<Item> oneLaptopBought() {
    return anItemBoughtEvent().of(anItem().withName("laptop")).build();
  }
}