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

/**
 * Repository interface for User entity.
 * Extends JpaRepository for CRUD operations and custom native sql query and
 * JPAQuery execution.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by first name, last name, and deletion status.
     * 
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param time      the deletion timestamp, to find if deleted at a specific
     *                  time
     * @return an Optional containing the found user or empty if not found
     */
    Optional<User> findByFirstNameAndLastNameAndDeletedEquals(@Param("FirstName") String firstName,
            @Param("LastName") String lastName, Instant time);

    /**
     * Marks a user as deleted by setting the deletion timestamp.
     * 
     * @param id   the user's id
     * @param time the timestamp marking when the user was deleted
     */
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.deleted =?2 WHERE u.id = ?1")
    void markAsDeleted(Long id, Instant time);

    /**
     * Finds users by their deleted status.
     * 
     * @param deleted the deletion timestamp
     * @return a list of users matching the deletion timestamp
     */
    List<User> findByDeleted(Instant deleted);

    /**
     * Performs a hard delete of a user by their id.
     * 
     * @param id the user's id
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM User u where u.id = ?1")
    void hardDeleteById(Long id);

    /**
     * Finds a user by id who is not deleted.
     * 
     * @param id   the user's id
     * @param time not used in the query, serves as a placeholder for consistency
     * @return an Optional containing the found user or empty if not found or
     *         deleted
     */
    @Query("SELECT u FROM User u WHERE u.id=?1 AND u.deleted IS NULL")
    Optional<User> findByIdAndNotDeleted(Long id, Instant time);

    /**
     * Finds a user by first name, last name, id, and deletion status using a native
     * query.
     * 
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param id        the user's id
     * @param time      the deletion timestamp or null for not deleted
     * @return an Optional containing the found user or empty if not found
     */
    @Query(value = "SELECT u.* FROM user u WHERE u.id = :id AND u.first_name = :firstName AND u.last_name = :lastName AND (:time IS NULL OR u.deleted = :time)", nativeQuery = true)
    Optional<User> findByFirstNameAndLastNameAndIdAndDeletedEquals(@Param("firstName") String firstName,
            @Param("lastName") String lastName, @Param("id") Long id, @Param("time") Instant time);
}
