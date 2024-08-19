package dev.brimming.utils.result;

import com.google.common.base.Preconditions;
import dev.brimming.helpers.DefaultStyle;
import java.util.List;
import java.util.Optional;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class ErrorList {

  abstract List<String> errorMessages();

  abstract Optional<String> correlationId();

  @Check
  protected void guardClauses() {
    Preconditions.checkArgument(!errorMessages().isEmpty(), "Error Messages cannot be empty");
  }

  public static Builder builder() {
    return ImmutableErrorList.builder();
  }

  public interface Builder {

    Builder addAllErrorMessages(Iterable<String> elements);

    Builder setErrorMessages(Iterable<String> elements);

    Builder setCorrelationId(Optional<String> correlationId);
  }
}
