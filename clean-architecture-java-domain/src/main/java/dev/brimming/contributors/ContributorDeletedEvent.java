package dev.brimming.contributors;

import dev.brimming.helpers.DefaultStyle;
import dev.brimming.baseclasses.BaseDomainEvent;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
@DefaultStyle
public abstract class ContributorDeletedEvent extends BaseDomainEvent {

  @Parameter
  public abstract long contributorId();
}
