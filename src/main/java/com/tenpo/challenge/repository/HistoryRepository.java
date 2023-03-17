package com.tenpo.challenge.repository;

import com.tenpo.challenge.model.entity.HistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    Page<HistoryEntity> findAll(Pageable pageable);

    HistoryEntity findTopByOrderByIdDesc();
}
