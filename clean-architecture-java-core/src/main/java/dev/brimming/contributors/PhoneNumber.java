package dev.brimming.contributors;

import com.google.errorprone.annotations.Immutable;
import dev.brimming.helpers.DefaultStyle;
import java.util.Optional;

@Immutable
@DefaultStyle
public abstract class PhoneNumber {

  public abstract String getCountryCode();

  public abstract String getNumber();

  public abstract Optional<String> getExtension();
}
