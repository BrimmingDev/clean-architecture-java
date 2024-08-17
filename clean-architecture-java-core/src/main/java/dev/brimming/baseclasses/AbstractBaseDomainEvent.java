package dev.brimming.baseclasses;

import dev.brimming.DefaultStyle;
import java.time.LocalDateTime;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class AbstractBaseDomainEvent {

  @Default
  public LocalDateTime getDateOccurred() {
    return LocalDateTime.now();
  }
}
