package com.keyin.SprintCRUD.AccessingDataMySQL;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "result", path = "result")
public interface ResultRepository extends PagingAndSortingRepository<Result, Integer> {
  List<Result> findByMemberOrderByResult(Member member);
  List<Result> findByTournamentOrderByResult(Tournament tournament);
}
