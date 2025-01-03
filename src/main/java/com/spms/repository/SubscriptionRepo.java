package com.spms.repository;


import com.spms.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {

//    Subscription findByUserId(Long userId);
}
