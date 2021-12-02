package com.keyin.SprintCRUD.AccessingDataMySQL;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tournament", path = "tournament")
public interface TournamentRepository extends PagingAndSortingRepository<Tournament, Integer> {
  List<Tournament> findByLocationContaining(@Param("searchQuery") String searchQuery);
  List<Tournament> findByStartDate(@Param("searchDate") String searchDate);
}
