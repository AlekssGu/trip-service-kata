package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    private static final User UNUSED_USER = null;
    private static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final Trip TRIP_TO_LONDON = new Trip();
    private static final Trip TRIP_TO_BRAZIL = new Trip();

    @Mock
    private TripDAO tripDAO;

    @InjectMocks
    private TripService tripService;

    @Test
    public void shouldNotReturnAnyTripsIfUsersAreNotFriends() {
        User user = UserBuilder.builder()
                .friendsWith(ANOTHER_USER)
                .withTrips(TRIP_TO_LONDON, TRIP_TO_BRAZIL)
                .build();

        List<Trip> tripsByUser = tripService.getFriendTrips(user, REGISTERED_USER);

        assertThat(tripsByUser).isEmpty();
    }

    @Test
    public void shouldReturnFriendsTrips() {
        User user = UserBuilder.builder()
                .friendsWith(ANOTHER_USER, REGISTERED_USER)
                .withTrips(TRIP_TO_LONDON, TRIP_TO_BRAZIL)
                .build();
        given(tripDAO.tripsBy(user)).willReturn(user.trips());

        List<Trip> friendTrips = tripService.getFriendTrips(user, REGISTERED_USER);

        assertThat(friendTrips).hasSize(2);
    }


    @Test(expected = UserNotLoggedInException.class)
    public void shouldThrowAnExceptionWhenUserIsNotLoggedIn() {
        tripService.getFriendTrips(UNUSED_USER, GUEST);
    }

}
