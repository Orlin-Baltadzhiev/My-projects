package com.example.Judge.Repository;

import com.example.Judge.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository <Comment,Long> {

    @Query("SELECT AVG(c.score) FROM Comment c ")
    Double findAvgScore();
}
