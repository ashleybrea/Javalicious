package com.fico.chatbot.repository;


import com.fico.chatbot.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Member entity
 * JpaRepository provides basic CRUD operations automatically
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
    // Spring automatically creates these methods based on the method names!
    
    /**
     * Find member by email address
     */
    Optional<Member> findByEmail(String email);
    
    /**
     * Find members by subscription type
     */
    List<Member> findBySubscriptionType(String subscriptionType);
    
    /**
     * Find members with FICO score above a certain value
     */
    List<Member> findByFicoScoreGreaterThan(Integer score);
    
    /**
     * Find members with FICO score below a certain value
     */
    List<Member> findByFicoScoreLessThan(Integer score);
    
    /**
     * Custom query to find members by name (first or last)
     */
    @Query("SELECT m FROM Member m WHERE " +
           "LOWER(m.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(m.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Member> findByNameContaining(@Param("name") String name);
    
    /**
     * Custom query to get average FICO score
     */
    @Query("SELECT AVG(m.ficoScore) FROM Member m")
    Double getAverageFicoScore();
}
