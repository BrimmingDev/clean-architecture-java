package dev.brimming.baseclasses;

import dev.brimming.helpers.DefaultStyle;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class BaseEntity {

  @Default
  public int id() {
    return 0;
  }

  public static Builder builder() {
    return ImmutableBaseEntity.builder();
  }

  public interface Builder {

    Builder setId(int id);

    BaseEntity build();
  }
}
