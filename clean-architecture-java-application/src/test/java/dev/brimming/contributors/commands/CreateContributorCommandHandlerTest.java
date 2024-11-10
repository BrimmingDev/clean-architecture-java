package dev.brimming.contributors.commands;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import dev.brimming.contributors.ContributorRepository;
import dev.brimming.contributors.ImmutableContributor;
import dev.brimming.utils.result.Result;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateContributorCommandHandlerTest {

  @Mock
  ContributorRepository contributorRepository;

  private CreateContributorCommandHandler handler;

  @BeforeEach
  void setup() {
    handler = new CreateContributorCommandHandler(contributorRepository);
  }

  @Test
  void itCreatesContributor() {
    ImmutableCreateContributorCommand immutableCreateContributorCommand = Instancio.create(
        ImmutableCreateContributorCommand.class);
    when(contributorRepository.create(any())).thenReturn(ImmutableContributor.of(
        immutableCreateContributorCommand.getName(), Optional.empty()));

    Result<Integer> result = handler.handle(immutableCreateContributorCommand).join();

    assertThat(result.isSuccess()).isTrue();
  }
}