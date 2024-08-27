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

  abstract List<String> getErrorMessages();

  abstract Optional<String> getCorrelationId();

  @Check
  protected void guardClauses() {
    Preconditions.checkArgument(!getErrorMessages().isEmpty(), "Error Messages cannot be empty");
  }
}
