package com.spms.service;


import com.spms.model.Subscription;
import com.spms.model.User;
import com.spms.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepo subscriptionRepo;


    @Override
    public Subscription createSubscription(User user) {
        return null;
    }
}
