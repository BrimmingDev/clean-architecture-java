package dev.brimming.baseclasses;

import dev.brimming.helpers.DefaultStyle;
import java.time.LocalDateTime;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class BaseDomainEvent {

  @Default
  public LocalDateTime dateOccurred() {
    return LocalDateTime.now();
  }
}
