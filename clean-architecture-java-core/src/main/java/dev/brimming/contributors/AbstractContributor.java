package dev.brimming.contributors;

import com.google.common.base.Preconditions;
import dev.brimming.DefaultStyle;
import dev.brimming.PhoneNumber;
import dev.brimming.baseclasses.AbstractBaseEntity;
import java.util.Optional;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class AbstractContributor extends AbstractBaseEntity {

  public abstract String getName();

  @Default
  public ContributorStatus getStatus() {
    return ContributorStatus.NOT_SET;
  }

  public abstract Optional<PhoneNumber> getPhoneNumber();

  @Check
  protected void guardClauses() {
    Preconditions.checkArgument(!getName().isEmpty(), "Name must not be empty");
  }
}
