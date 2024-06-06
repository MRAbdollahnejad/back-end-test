package com.example.demo.repository;

import com.example.demo.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory,Long> {

    @Query(value = "select * from search_history h where h.users_entity_id = :id ",nativeQuery = true)
    Optional<List<SearchHistory>> customFindAll(@Param("id") Long id);
}
