package com.gdipaolantonio.ripostiglio.eventstore;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;

import org.junit.Test;

import com.gdipaolantonio.ripostiglio.domain.Event;
import com.gdipaolantonio.ripostiglio.domain.EventStub;

public class InMemoryEventStoreTest {

  private static final EventStub<String> FIRST_EVENT = new EventStub<String>(Instant.ofEpochSecond(1), "the body of the first event");
  private static final EventStub<String> SECOND_EVENT = new EventStub<String>(Instant.ofEpochSecond(2), "the body of the second event");
  private static final EventStub<String> THIRD_EVENT = new EventStub<String>(Instant.ofEpochSecond(3), "the body of the third event");

  @Test
  public void appendAnEventOnEmptyEventStore() throws Exception {

    Collection<Event<?>> events = new ConcurrentLinkedQueue<>();

    new InMemoryEventStore(events).append(FIRST_EVENT);

    assertThat(events, contains(FIRST_EVENT));
  }

  @Test
  public void appendManyEventsOnEmptyEventStore() throws Exception {

    Collection<Event<?>> events = new ConcurrentLinkedQueue<>();

    EventStore eventStore = new InMemoryEventStore(events);
    eventStore.append(FIRST_EVENT);
    eventStore.append(SECOND_EVENT);
    eventStore.append(THIRD_EVENT);

    assertThat(events, contains(FIRST_EVENT, SECOND_EVENT, THIRD_EVENT));
  }

  @Test
  public void getEventsOnEmptyEventStore() throws Exception {

    EventStore eventStore = new InMemoryEventStore(emptyList());

    Stream<Event<?>> events = eventStore.events();

    assertThat(events.count(), is(0L));
  }

  @Test
  public void getEventsOnFullEventStore() throws Exception {

    EventStore eventStore = new InMemoryEventStore(asList(FIRST_EVENT, SECOND_EVENT, THIRD_EVENT));

    Stream<Event<?>> events = eventStore.events();

    assertThat(events.collect(toList()), contains(FIRST_EVENT, SECOND_EVENT, THIRD_EVENT));
  }

  @Test
  public void modifyingEventsDoesntAffectEventStoreInternalState() throws Exception {

    EventStore eventStore = new InMemoryEventStore(asList(FIRST_EVENT, SECOND_EVENT, THIRD_EVENT));

    eventStore.events().filter(event -> event.created().equals(Instant.ofEpochSecond(1)));

    assertThat(eventStore.events().collect(toList()), contains(FIRST_EVENT, SECOND_EVENT, THIRD_EVENT));
  }
}
