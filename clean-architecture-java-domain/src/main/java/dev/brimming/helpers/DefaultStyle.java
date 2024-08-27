package dev.brimming.helpers;

import org.immutables.value.Value.Style;

@Style(
    get = {"is*", "get*"},
    init = "set*")
public @interface DefaultStyle {

}
