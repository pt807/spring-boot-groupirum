package com.green.groupirum.repository;

import com.green.groupirum.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByRecruitIdAndParentIsNullOrderByIdDesc(Long recruitId);
}
