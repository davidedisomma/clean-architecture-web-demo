package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out;

import org.ucieffe.cleanarchitecture.library.entity.Loan;

public interface PersistLoan {
    void persist(Loan loan);
}
