package com.gdipaolantonio.ripostiglio.eventstore;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

import com.gdipaolantonio.ripostiglio.domain.Event;
import com.gdipaolantonio.ripostiglio.domain.EventStub;

public class InMemoryEventStoreTest {

  @Test
  public void appendAnEventOnEmptyEventStore() throws Exception {

    EventStub<String> event = new EventStub<String>(Instant.ofEpochSecond(1), "the body of the event");
    Collection<Event<?>> events = new ConcurrentLinkedQueue<>();

    new InMemoryEventStore(events).store(event);

    assertThat(events, contains(event));
  }

  @Test
  public void appendManyEventsOnEmptyEventStore() throws Exception {

    EventStub<String> firstEvent = new EventStub<String>(Instant.ofEpochSecond(1), "the body of the first event");
    EventStub<String> secondEvent = new EventStub<String>(Instant.ofEpochSecond(2), "the body of the second event");
    EventStub<String> thirdEvent = new EventStub<String>(Instant.ofEpochSecond(3), "the body of the third event");

    Collection<Event<?>> events = new ConcurrentLinkedQueue<>();

    InMemoryEventStore eventStore = new InMemoryEventStore(events);
    eventStore.store(firstEvent);
    eventStore.store(secondEvent);
    eventStore.store(thirdEvent);

    assertThat(events, contains(firstEvent, secondEvent, thirdEvent));
  }
}
