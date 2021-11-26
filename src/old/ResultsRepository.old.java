package com.keyin.SprintCRUD.AccessingDataMySQL;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "results", path = "results")
public interface ResultsRepository extends PagingAndSortingRepository<Results, Integer> {
}
