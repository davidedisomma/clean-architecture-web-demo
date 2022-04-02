package org.ucieffe.cleanarchitecture.library.entity;

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

        loan.end(TODAY);

        assertTrue(loan.isClosed());
        assertEquals(TODAY, loan.getEndDate());
        assertFalse(Fixtures.TDD_BY_EXAMPLE_COPY_5.isReserved());
    }

    @Test
    void renew_book_extending_due_date_with_given_days() {
        Loan loan = new LoanBuilder()
                .withDueDate(TEN_DAYS_LATER)
                .build();

        loan.renew(30);

        assertFalse(loan.isClosed());
        assertEquals(FORTY_DAYS_LATER, loan.getDueDate());
    }

    @Test
    void renew_book_not_overcoming_maximum_loan_date() {
        Loan loan = new LoanBuilder()
                .withMaximumDueDate(TWENTY_DAYS_LATER)
                .withDueDate(TODAY)
                .build();

        loan.renew(30);

        assertFalse(loan.isClosed());
        assertEquals(TWENTY_DAYS_LATER, loan.getDueDate());
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