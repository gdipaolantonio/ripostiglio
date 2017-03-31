package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemAddedEventBuilder.anAddItemEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static java.util.stream.Stream.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

public class StorageStatusTest {

  @Test
  public void withAnEmptyStream() throws Exception {

    StorageStatus status = StorageStatus.of(empty());

    long count = status.quantityFor(new ItemKey("item name"));

    assertThat(count, is(0L));
  }

  @Test
  public void countQuantitiesWithOneTypeOfItem() throws Exception {

    StorageStatus status = StorageStatus.of(
      Stream.of(
        addOneSmartphone(),
        addOneSmartphone(),
        addOneSmartphone()
      )
    );

    long count = status.quantityFor(new ItemKey("smartphone"));

    assertThat(count, is(3L));
  }

  @Test
  @Ignore
  public void countQuantitiesWithManyTypesOfItem() throws Exception {

    StorageStatus status = StorageStatus.of(
      Stream.of(
        addOneSmartphone(),
        addOneSmartphone(),
        addOneSmartphone(),
        addOneLaptop(),
        addOneTablet(),
        addOneTablet()
      )
    );

    long smartphoneCount = status.quantityFor(new ItemKey("smartphone"));
    long tabletCount = status.quantityFor(new ItemKey("tablet"));
    long laptopCount = status.quantityFor(new ItemKey("laptop"));

    assertThat(smartphoneCount, is(3L));
    assertThat(tabletCount, is(2L));
    assertThat(laptopCount, is(1L));
  }

  private Event<Item> addOneSmartphone() {
    return anAddItemEvent().of(anItem().withName("smartphone")).build();
  }

  private Event<Item> addOneTablet() {
    return anAddItemEvent().of(anItem().withName("tablet")).build();
  }

  private Event<Item> addOneLaptop() {
    return anAddItemEvent().of(anItem().withName("laptop")).build();
  }
}
