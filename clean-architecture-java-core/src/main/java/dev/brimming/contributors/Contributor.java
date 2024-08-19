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
  public abstract String name();

  @Default
  public ContributorStatus status() {
    return ContributorStatus.NOT_SET;
  }

  @Parameter
  public abstract Optional<PhoneNumber> phoneNumber();

  @Check
  protected void guardClauses() {
    Preconditions.checkArgument(!name().isEmpty(), "Name must not be empty");
  }

  public static Contributor copyOf(Contributor contributor) {
    return ImmutableContributor.copyOf(contributor);
  }

  public static Builder builder() {
    return ImmutableContributor.builder();
  }

  public interface Builder extends BaseEntity.Builder {

    //TODO figure out why Immutables won't extend BaseEntity.Builder
    @Override
    Builder setId(int id);

    Builder setName(String name);

    Builder setStatus(ContributorStatus status);

    Builder setPhoneNumber(Optional<? extends PhoneNumber> phoneNumber);

    Contributor build();
  }
}
