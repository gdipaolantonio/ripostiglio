package com.gdipaolantonio.ripostiglio.eventstore;

import java.util.stream.Stream;

import com.gdipaolantonio.ripostiglio.domain.Event;

public interface EventStore {

  void store(Event<?> event);

  Stream<Event<?>> events();
}
