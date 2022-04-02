package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out;

import org.ucieffe.cleanarchitecture.library.entity.User;

public interface GetUserDetails {
    User findBy(String userId);
}
