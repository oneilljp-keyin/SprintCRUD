package com.keyin.SprintCRUD.AccessingDataMySQL;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "member", path = "member")
public interface MemberRepository extends PagingAndSortingRepository<Member, Integer> {
  List<Member> findByNameStartsWith(@Param("firstLetter") String firstLetter);
  List<Member> findByNameContaining(@Param("searchQuery") String searchQuery);
}
