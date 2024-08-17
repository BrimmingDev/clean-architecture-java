package dev.brimming;

import org.immutables.value.Value.Style;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Style(
    get = {"is*", "get*"},
    init = "set*",
    typeImmutable = "*",
    visibility = ImplementationVisibility.PUBLIC)
public @interface DefaultStyle {

}
