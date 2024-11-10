package dev.brimming.helpers;

import dev.brimming.helpers.mediator.Command;
import org.immutables.value.Value.Immutable;

@Immutable
@DefaultStyle
public interface TestCommand extends Command<String> {

}
