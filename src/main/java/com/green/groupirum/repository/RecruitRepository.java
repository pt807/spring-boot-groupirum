package com.green.groupirum.repository;

import com.green.groupirum.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    //insert, update, delete쿼리 사용시 필요함
    @Modifying
    @Query("update Recruit r set r.views = r.views + 1 where r.id = :id")
    int updateViews(Long id);

}
