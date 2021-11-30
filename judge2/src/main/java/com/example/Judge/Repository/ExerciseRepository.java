package com.example.Judge.Repository;

import com.example.Judge.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e.name FROM Exercise e " +
            "ORDER by e.name")
    List<String> findAllExNames();

   Optional<Exercise> findByName(String name);

}
