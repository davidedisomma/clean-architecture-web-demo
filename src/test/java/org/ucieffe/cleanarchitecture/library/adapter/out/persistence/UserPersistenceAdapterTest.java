package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ucieffe.cleanarchitecture.library.entity.User;

import java.time.LocalDate;

import static java.lang.Boolean.FALSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserPersistenceAdapterTest {

    @Autowired
    private UserPersistenceAdapter userPersistenceAdapter;

    @Autowired
    private UserRepository userRepository;

    @Test
    void find_user_by_id() {
        UserJpaEntity aUser = createUserWithId("a_user_id");

        User user = userPersistenceAdapter.findBy("a_user_id");

        Assertions.assertNotNull(user);
    }

    @Test
    void throw_exception_when_user_id_does_not_exist() {
        UserJpaEntity aUser = createUserWithId("a_user_id");
        userRepository.saveAndFlush(aUser);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userPersistenceAdapter.findBy("an_no_existent_user_id");
        });

        assertEquals("Impossible to find user with id an_no_existent_user_id", thrown.getMessage());
    }

    private UserJpaEntity createUserWithId(final String userId) {
        UserJpaEntity user = new UserJpaEntity();
        user.setId(userId);
        user.setLastName("John");
        user.setFirstName("Doe");
        user.setBirthDate(LocalDate.of(1999, 5, 14));
        user.setSuspended(FALSE);
        userRepository.saveAndFlush(user);
        return user;
    }
}