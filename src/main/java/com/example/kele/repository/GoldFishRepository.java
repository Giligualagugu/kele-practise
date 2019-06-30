package com.example.kele.repository;

import com.example.kele.entity.GoldFishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoldFishRepository extends JpaRepository<GoldFishEntity, String> {
    @Query(value = "select group_id from goldfish_info group by group_id having count(*)<3", nativeQuery = true)
    List<String> findGroupIdGroupByGroupId();

    List<GoldFishEntity> findByGroupId(String e);
}
