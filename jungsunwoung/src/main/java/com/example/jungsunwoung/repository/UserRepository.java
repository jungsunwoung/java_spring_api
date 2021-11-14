package com.example.jungsunwoung.repository;

import com.example.jungsunwoung.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
//    @Query("select u from User u")
    @Query("select distinct u from User u join fetch u.calculationHistoryList c where u.id =:id and c.timeStamp >= :start and c.timeStamp <= :end")
    User findByIdAndStartAndEnd(@Param("id") String userId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
