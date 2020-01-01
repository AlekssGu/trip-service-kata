package org.craftedsw.tripservicekata.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class UserTest {

    private static final User A_GUY = new User();
    private static final User ANOTHER_GUY = new User();

    @Test
    public void shouldReturnFalseIfNotFriends() {
        User user = UserBuilder.builder()
                .friendsWith(A_GUY)
                .build();

        Assertions.assertThat(user.isFriendsWith(ANOTHER_GUY)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfUsersAreFriend() {
        User user = UserBuilder.builder()
                .friendsWith(ANOTHER_GUY, A_GUY)
                .build();

        Assertions.assertThat(user.isFriendsWith(A_GUY)).isTrue();
    }

}
