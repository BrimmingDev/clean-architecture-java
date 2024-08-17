package dev.brimming.contributors;

import java.util.List;

public interface ContributorRepository {

  Contributor create(Contributor contributor);

  Contributor update(Contributor contributor);

  Contributor delete(Contributor contributor);

  Contributor getById(Long id);

  List<Contributor> getContributorsPaged(int offset, int limit);
}
