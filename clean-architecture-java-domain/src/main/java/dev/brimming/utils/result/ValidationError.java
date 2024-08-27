package dev.brimming.utils.result;

import dev.brimming.helpers.DefaultStyle;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public abstract class ValidationError {

  abstract String getIdentifier();

  abstract String getErrorMessage();

  abstract String getErrorCode();

  abstract ValidationSeverity getServerity();
}
