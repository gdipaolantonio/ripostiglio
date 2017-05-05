package com.gdipaolantonio.ripostiglio;

import static java.lang.String.format;
import static java.time.Clock.systemDefaultZone;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.gdipaolantonio.ripostiglio.domain.Item;
import com.gdipaolantonio.ripostiglio.domain.ItemKey;
import com.gdipaolantonio.ripostiglio.domain.SimpleEventFactory;
import com.gdipaolantonio.ripostiglio.domain.StorageCommand;
import com.gdipaolantonio.ripostiglio.domain.StorageQuery;
import com.gdipaolantonio.ripostiglio.eventstore.EventStore;
import com.gdipaolantonio.ripostiglio.eventstore.InMemoryEventStore;

public class Main {

  private static final Item TABLET = new Item(new ItemKey("tablet"));
  private static final Item LAPTOP = new Item(new ItemKey("laptop"));
  private static final Item SMARTPHONE = new Item(new ItemKey("smartphone"));

  public static void main(String[] args) {

    EventStore eventStore = new InMemoryEventStore(new ConcurrentLinkedQueue<>());

    StorageCommand command = new StorageCommand(new SimpleEventFactory(systemDefaultZone()), eventStore);

    command.store(SMARTPHONE);
    command.store(LAPTOP);
    command.store(TABLET);
    command.store(SMARTPHONE);
    command.store(SMARTPHONE);
    command.store(TABLET);
    command.evict(TABLET);

    StorageQuery query = new StorageQuery(eventStore);

    System.out.println("## Event log ##");
    eventStore.events().forEach(System.out::println);
    System.out.println();

    System.out.println("## Storage status ##");
    System.out.println(printQuantity(query, "smartphone"));
    System.out.println(printQuantity(query, "laptop"));
    System.out.println(printQuantity(query, "tablet"));
  }

  private static String printQuantity(StorageQuery query, String name) {
    return format("Quantity of '%s': %d", name, query.quantityFor(new ItemKey(name)));
  }
}
