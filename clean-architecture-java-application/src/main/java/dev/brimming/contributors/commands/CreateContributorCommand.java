package dev.brimming.contributors.commands;

import dev.brimming.helpers.DefaultStyle;
import dev.brimming.helpers.mediator.Command;
import dev.brimming.utils.result.Result;
import java.util.Optional;
import org.immutables.value.Value.Immutable;

@DefaultStyle
@Immutable
public interface CreateContributorCommand extends Command<Result<Integer>> {

  String getName();

  Optional<String> getPhoneNumber();
}
