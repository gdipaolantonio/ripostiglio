package com.gdipaolantonio.ripostiglio.domain;

import java.time.Instant;

public interface Event<Body> {

  Instant created();

  Body body();
}