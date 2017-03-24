package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.AddItemEventBuilder.anAddItemEvent;
import static com.gdipaolantonio.ripostiglio.domain.EventStubBuilder.anEventStub;
import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private EventFactory factory;

  @Mock
  private EventStore eventStore;

  @Test
  public void addAnItem() throws Exception {

    Item itemToBeAdded = anItem().build();
    Event<Item> event = anAddItemEvent().build();

    context.checking(new Expectations() {{
      allowing(factory).newAddItemEvent(itemToBeAdded); will(returnValue(event));
      oneOf(eventStore).store(event);
    }});

    new Storage(factory, eventStore).addItem(itemToBeAdded);
  }

  @Test
  public void getStorageStatus() throws Exception {

    Stream<Event<?>> events = Stream.of(anEventStub().build());
    StorageStatus expected = new StorageStatus(events);

    context.checking(new Expectations() {{
      allowing(eventStore).events(); will(returnValue(events));
    }});

    StorageStatus status = new Storage(factory, eventStore).status();

    assertThat(status, is(expected));
  }
}
