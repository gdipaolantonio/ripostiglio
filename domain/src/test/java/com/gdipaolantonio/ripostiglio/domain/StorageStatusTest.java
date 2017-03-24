package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.AddItemEventBuilder.anAddItemEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.util.stream.Stream.empty;
import static java.util.stream.Stream.of;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StorageStatusTest {

  @Test
  public void withAnEmptyStream() throws Exception {

    StorageStatus status = new StorageStatus(empty());

    long count = status.quantityFor(new ItemKey("item name"));

    assertThat(count, is(0L));
  }

  @Test
  public void countQuantitiesWithOneTypeOfItem() throws Exception {

    StorageStatus status = new StorageStatus(
      of(
        addOneSmartphone(),
        addOneSmartphone(),
        addOneSmartphone()
      )
    );

    long count = status.quantityFor(new ItemKey("smartphone"));

    assertThat(count, is(3L));
  }

  private Event<Item> addOneSmartphone() {
    return anAddItemEvent().of(anItem().withName("smartphone")).build();
  }
}
