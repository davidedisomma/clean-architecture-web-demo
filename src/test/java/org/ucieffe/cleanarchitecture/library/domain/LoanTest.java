package org.ucieffe.cleanarchitecture.library.domain;

import org.junit.jupiter.api.Test;
import org.ucieffe.cleanarchitecture.library.Fixtures;

import static org.junit.jupiter.api.Assertions.*;
import static org.ucieffe.cleanarchitecture.library.Fixtures.*;

class LoanTest {

    @Test
    void give_back_the_book() {
        Loan loan = new LoanBuilder()
                .withItem(Fixtures.TDD_BY_EXAMPLE_COPY_5)
                .build();

        loan.end(today);

        assertTrue(loan.isClosed());
        assertEquals(today, loan.getEndDate());
        assertFalse(Fixtures.TDD_BY_EXAMPLE_COPY_5.isReserved());
    }

    @Test
    void renew_book_extending_due_date_with_given_days() {
        Loan loan = new LoanBuilder()
                .withDueDate(tenDaysLater)
                .build();

        loan.renew(30);

        assertFalse(loan.isClosed());
        assertEquals(fortyDaysLater, loan.getDueDate());
    }

    @Test
    void renew_book_not_overcoming_maximum_loan_date() {
        Loan loan = new LoanBuilder()
                .withMaximumLoanDate(twentyDaysLater)
                .withDueDate(today)
                .build();

        loan.renew(30);

        assertFalse(loan.isClosed());
        assertEquals(twentyDaysLater, loan.getDueDate());
    }

    @Test
    void increment_counter_whenever_renew_happens() {
        Loan loan = new LoanBuilder()
                .withHowManyRenewals(0)
                .build();

        loan.renew(10);
        assertEquals(1, loan.getHowManyRenewals());
        loan.renew(10);
        assertEquals(2, loan.getHowManyRenewals());
        loan.renew(10);
        assertEquals(3, loan.getHowManyRenewals());
    }
}