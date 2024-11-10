package dev.brimming.contributors;

import dev.brimming.helpers.mediator.Event;
import dev.brimming.helpers.DefaultStyle;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
@DefaultStyle
public abstract class ContributorDeletedEvent implements Event {

  @Parameter
  public abstract long contributorId();
}
