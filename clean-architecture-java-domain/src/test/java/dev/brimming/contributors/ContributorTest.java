package dev.brimming.contributors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class ContributorTest {

  @Test
  public void itDoesNotAllowEmptyGetName() {
    assertThrows(IllegalArgumentException.class,
        () -> Instancio.create(ImmutableContributor.class).withName(""));
  }

  @Test
  public void itDefaultsContributorGetStatusToNotSet() {
    Contributor contributor = ImmutableContributor.builder().setId(1).setName("blah").build();

    assertThat(contributor.getStatus()).isEqualTo(ContributorStatus.NOT_SET);
  }
}