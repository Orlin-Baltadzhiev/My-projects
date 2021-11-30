package com.example.Judge.Repository;

import com.example.Judge.model.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Long> {

    Optional<Homework> findTop1ByOrderByComments();
}
