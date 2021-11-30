package com.example.Judge.Repository;

import java.util.List;
import com.example.Judge.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

        Optional<User> findByUsernameAndPassword(String username, String password);

        @Query("SELECT u.username FROM User u " +
                "ORDER BY u.username ")
        List<String> findAllUsernames();

        Optional<User> findByUsername(String username);
}
