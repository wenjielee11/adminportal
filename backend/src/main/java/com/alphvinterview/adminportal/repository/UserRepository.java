package com.alphvinterview.adminportal.repository;

import com.alphvinterview.adminportal.model.*;

import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByFirstNameAndLastNameAndDeletedEquals(@Param("FirstName") String firstName, @Param("LastName") String lastName, Instant time);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.deleted =?2 WHERE u.id = ?1")
    void markAsDeleted(Long id, Instant time);

    
    List<User> findByDeleted(Instant deleted);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u where u.id = ?1")
    void hardDeleteById(Long id);
    
    @Query("SELECT u FROM User u WHERE u.id=?1 AND u.deleted IS NULL")
    Optional<User> findByIdAndNotDeleted(Long id, Instant time);

    @Query(value = "SELECT u.* FROM user u WHERE u.id = :id AND u.first_name = :firstName AND u.last_name = :lastName AND (:time IS NULL OR u.deleted = :time)", nativeQuery = true)
    Optional<User> findByFirstNameAndLastNameAndIdAndDeletedEquals(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("id") Long id, @Param("time") Instant time);


}   
