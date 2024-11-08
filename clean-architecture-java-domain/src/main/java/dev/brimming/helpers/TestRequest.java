package dev.brimming.helpers;

import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public interface TestRequest extends Request<String> {

  String getMessage();
}
