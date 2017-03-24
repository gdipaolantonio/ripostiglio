package com.gdipaolantonio.ripostiglio.eventstore;

import com.gdipaolantonio.ripostiglio.domain.Event;

public interface EventStore {

  void store(Event<?> event);
}
