package dev.brimming.utils.result;

import dev.brimming.helpers.DefaultStyle;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class ValidationError {

  abstract String identifier();

  abstract String errorMessage();

  abstract String errorCode();

  abstract ValidationSeverity severity();

  public static Builder builder() {
    return ImmutableValidationError.builder();
  }

  public interface Builder {

    Builder setIdentifier(String identifier);

    Builder setErrorMessage(String errorMessage);

    Builder setErrorCode(String errorCode);

    Builder setSeverity(ValidationSeverity severity);
  }
}
