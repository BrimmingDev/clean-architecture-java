package dev.brimming.helpers.mediator;

import java.time.LocalDateTime;


public interface Event {

  default LocalDateTime dateOccurred() {
    return LocalDateTime.now();
  }
}
