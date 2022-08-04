package com.green.groupirum.repository;

import com.green.groupirum.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("SELECT r FROM Reply r WHERE r.recruit.id = :recruitId ORDER BY coalesce(r.parent.id, r.id) , r.id")
    List<Reply> findAllByRecruitId(@Param("recruitId") Long recruitId);

    Page<Reply> findAllByMember_Nickname(String nickname, Pageable pageable);

}
