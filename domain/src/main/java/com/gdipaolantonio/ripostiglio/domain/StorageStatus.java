package com.gdipaolantonio.ripostiglio.domain;

import java.util.Objects;
import java.util.stream.Stream;

public class StorageStatus {

  private final Stream<Event<?>> events;

  public static StorageStatus of(Stream<Event<?>> events) {
    return new StorageStatus(events);
  }

  private StorageStatus(Stream<Event<?>> events) {
    this.events = events;
  }

  public long quantityFor(ItemKey itemKey) {
    return events.count();
  }

  @Override
  public int hashCode() {
    return Objects.hash(events);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    StorageStatus other = (StorageStatus) obj;
    return Objects.equals(events, other.events);
  }

  @Override
  public String toString() {
    return "StorageStatus [events=" + events + "]";
  }
}
