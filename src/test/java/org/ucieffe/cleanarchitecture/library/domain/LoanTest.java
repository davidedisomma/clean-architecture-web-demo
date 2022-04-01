package org.ucieffe.cleanarchitecture.library.domain;

import org.junit.jupiter.api.Test;
import org.ucieffe.cleanarchitecture.library.Fixtures;

import java.util.UUID;

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
    void renew_book_extending_due_date() {
        Loan loan = new LoanBuilder()
                .withDueDate(tenDaysLater)
                .build();

        loan.renew(30);

        assertFalse(loan.isClosed());
        assertEquals(fortyDaysLater, loan.getDueDate());
    }
}