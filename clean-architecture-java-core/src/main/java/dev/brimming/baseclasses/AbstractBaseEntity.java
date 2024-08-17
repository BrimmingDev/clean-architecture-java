package dev.brimming.baseclasses;

import dev.brimming.DefaultStyle;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class AbstractBaseEntity {

  @Default
  public int getId() {
    return 0;
  }
}
