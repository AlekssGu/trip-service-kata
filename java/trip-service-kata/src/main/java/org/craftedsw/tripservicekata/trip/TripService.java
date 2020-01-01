package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class TripService {

    @Autowired
    private TripDAO tripDAO;

    public List<Trip> getFriendTrips(User user, User loggedInUser) throws UserNotLoggedInException {
        validate(loggedInUser);

        return user.isFriendsWith(loggedInUser)
                ? tripsBy(user)
                : noTrips();
    }

    private void validate(User loggedInUser) {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    private List<Trip> noTrips() {
        return Collections.emptyList();
    }

    private List<Trip> tripsBy(User user) {
        return tripDAO.tripsBy(user);
    }

}
