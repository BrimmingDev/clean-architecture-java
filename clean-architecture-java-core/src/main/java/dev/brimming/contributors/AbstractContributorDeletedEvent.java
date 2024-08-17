package dev.brimming.contributors;

import dev.brimming.DefaultStyle;
import dev.brimming.baseclasses.AbstractBaseDomainEvent;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
@DefaultStyle
public abstract class AbstractContributorDeletedEvent extends AbstractBaseDomainEvent {

  @Parameter
  public abstract int getContributorId();
}
