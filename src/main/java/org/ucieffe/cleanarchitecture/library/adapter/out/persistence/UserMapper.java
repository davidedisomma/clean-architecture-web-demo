package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.ucieffe.cleanarchitecture.library.entity.User;

public class UserMapper {

    public static User mapToUser(UserJpaEntity userJpaEntity) {
        return new User(
                userJpaEntity.getId(),
                userJpaEntity.getFirstName(),
                userJpaEntity.getLastName(),
                userJpaEntity.getBirthDate(),
                userJpaEntity.getSuspended()
        );
    }
}
