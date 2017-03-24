package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.AddItemEventBuilder.anAddItemEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;

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
    Event event = anAddItemEvent().build();

    context.checking(new Expectations() {{
      allowing(factory).newAddItemEvent(itemToBeAdded); will(returnValue(event));
      oneOf(eventStore).store(event);
    }});

    new Storage(factory, eventStore).addItem(itemToBeAdded);
  }
}
