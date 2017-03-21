package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.Test;

public class SimpleEventFactoryTest {

  @Test
  public void createAnAddItemEvent() throws Exception {

    Instant fixedInstant = Instant.parse("2017-03-21T22:35:18.123Z");
    Clock clock = Clock.fixed(fixedInstant, ZoneId.of("+01"));
    Item item = anItem().withName("item name").build();

    AddItemEvent event = new SimpleEventFactory(clock).newAddItemEvent(item);

    assertThat(event.created(), is(fixedInstant));
    assertThat(event.body(), is(item));
  }
}