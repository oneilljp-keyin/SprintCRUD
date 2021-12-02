package com.keyin.SprintCRUD.AccessingDataMySQL;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "results", path = "results")
public interface ResultRepository extends PagingAndSortingRepository<Result, Integer> {
  List<Result> findByMember(Optional<Member> member);
  List<Result> findByTournamentOrderByResult(Optional<Tournament> tournament);
}
