package letscode.sarafan.service;

import letscode.sarafan.domain.User;
import letscode.sarafan.domain.UserSubscription;
import letscode.sarafan.repo.UserDetailsRepo;
import letscode.sarafan.repo.UserSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserDetailsRepo userDetailsRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;

    @Autowired
    public ProfileService(UserDetailsRepo userDetailsRepo, UserSubscriptionRepo userSubscriptionRepo) {
        this.userDetailsRepo = userDetailsRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription ->
                        subscription.getSubscriber().equals(subscriber)
                )
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            subscriptions.forEach(channel.getSubscribers()::remove);
        }

        return userDetailsRepo.save(channel);
    }

    public List<UserSubscription> getSubscribers(User channel) {
        return userSubscriptionRepo.findByChannel(channel);
    }

    public UserSubscription changeSubscriptionsStatus(User channel, User subscriber) {
        UserSubscription subscription = userSubscriptionRepo.findByChannelAndSubscriber(channel, subscriber);
        subscription.setActive(!subscription.isActive());
        return userSubscriptionRepo.save(subscription);
    }

}