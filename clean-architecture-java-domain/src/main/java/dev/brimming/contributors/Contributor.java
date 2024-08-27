package dev.brimming.contributors;

import com.google.common.base.Preconditions;
import dev.brimming.helpers.DefaultStyle;
import dev.brimming.baseclasses.BaseEntity;
import java.util.Optional;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

@Immutable
@DefaultStyle
public abstract class Contributor extends BaseEntity {

  @Parameter
  public abstract String getName();

  @Default
  public ContributorStatus getStatus() {
    return ContributorStatus.NOT_SET;
  }

  @Parameter
  public abstract Optional<PhoneNumber> getPhoneNumber();

  @Check
  protected void guardClauses() {
    Preconditions.checkArgument(!getName().isEmpty(), "Name must not be empty");
  }
}
