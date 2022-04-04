package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucieffe.cleanarchitecture.library.entity.User;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.GetUserDetails;

@Service
public class UserPersistenceAdapter implements GetUserDetails {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findBy(String userId) {
        UserJpaEntity userJpaEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Impossible to find user with id " + userId));
        return UserMapper.mapToUser(userJpaEntity);
    }
}
