package dev.brimming.contributors;

import org.immutables.value.Value.Immutable;
import dev.brimming.helpers.DefaultStyle;
import java.util.Optional;
import org.immutables.value.Value.Parameter;

@Immutable
@DefaultStyle
public abstract class PhoneNumber {

  @Parameter
  public abstract String getCountryCode();

  @Parameter
  public abstract String getNumber();

  @Parameter
  public abstract Optional<String> getExtension();
}
