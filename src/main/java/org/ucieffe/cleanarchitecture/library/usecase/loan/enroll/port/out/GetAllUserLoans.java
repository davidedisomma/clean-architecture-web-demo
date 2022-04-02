package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out;

import org.ucieffe.cleanarchitecture.library.entity.Loan;

import java.util.List;

public interface GetAllUserLoans {
    List<Loan> findBy(String userId);
}
