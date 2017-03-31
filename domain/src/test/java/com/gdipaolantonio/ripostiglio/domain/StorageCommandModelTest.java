package com.gdipaolantonio.ripostiglio.domain;

import static com.gdipaolantonio.ripostiglio.domain.ItemBoughtEventBuilder.anItemBoughtEvent;
import static com.gdipaolantonio.ripostiglio.domain.ItemBuilder.anItem;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.gdipaolantonio.ripostiglio.eventstore.EventStore;

public class StorageCommandModelTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock
  private EventFactory factory;

  @Mock
  private EventStore eventStore;

  @Test
  public void addAnItem() throws Exception {

    Item itemToBeAdded = anItem().build();
    Event<Item> event = anItemBoughtEvent().build();

    context.checking(new Expectations() {{
      allowing(factory).newItemBoughtEvent(itemToBeAdded); will(returnValue(event));
      oneOf(eventStore).append(event);
    }});

    new StorageCommandModel(factory, eventStore).buyItem(itemToBeAdded);
  }
}
