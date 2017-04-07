package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemStoredEventBuilder.anItemStoredEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageCommandTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private EventFactory factory;

  @Mock
  private EventStore eventStore;

  @Test
  public void storeAnItem() throws Exception {

    Item itemToBeStored = anItem().build();
    Event<Item> event = anItemStoredEvent().build();

    context.checking(new Expectations() {{
      allowing(factory).newItemStoredEvent(itemToBeStored); will(returnValue(event));
      oneOf(eventStore).append(event);
    }});

    new StorageCommand(factory, eventStore).storeItem(itemToBeStored);
  }
}
