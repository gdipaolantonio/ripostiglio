package com.gdipaolantonio.ripostiglio.domain;

import java.util.Objects;

public class ItemKey {

  private final String name;

  public ItemKey(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ItemKey other = (ItemKey) obj;

    return Objects.equals(name, other.name);
  }

  @Override
  public String toString() {
    return "ItemKey [name=" + name + "]";
  }
}
