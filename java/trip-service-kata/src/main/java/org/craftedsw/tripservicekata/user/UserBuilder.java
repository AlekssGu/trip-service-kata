package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserBuilder {

    private List<User> friends = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder friendsWith(User... friends) {
        this.friends = Arrays.asList(friends);
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = Arrays.asList(trips);
        return this;
    }

    public User build() {
        User user = new User();
        friends.forEach(user::addFriend);
        trips.forEach(user::addTrip);
        return user;
    }
}
