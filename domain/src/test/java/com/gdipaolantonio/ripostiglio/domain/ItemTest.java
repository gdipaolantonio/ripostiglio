package com.gdipaolantonio.ripostiglio.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemTest {

  @Test
  public void doesntMatchKey() throws Exception {

    Item item = new Item(new ItemKey("matching"));

    boolean matches = item.hasKey(new ItemKey("not matching"));

    assertFalse(matches);
  }

  @Test
  public void matchesKey() throws Exception {

    Item item = new Item(new ItemKey("matching"));

    boolean matches = item.hasKey(new ItemKey("matching"));

    assertTrue(matches);
  }
}
