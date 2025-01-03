package com.spms.service;


import com.spms.model.PlanType;
import com.spms.model.Subscription;
import com.spms.model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);



}
