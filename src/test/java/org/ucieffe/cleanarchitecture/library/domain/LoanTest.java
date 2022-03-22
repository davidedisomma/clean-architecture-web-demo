package org.ucieffe.cleanarchitecture.library.domain;

import org.junit.jupiter.api.Test;
import org.ucieffe.cleanarchitecture.library.Fixtures;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.ucieffe.cleanarchitecture.library.Fixtures.*;

class LoanTest {

    @Test
    void give_back_the_book() {
        Loan loan = new Loan(
                UUID.randomUUID(),
                Fixtures.TDD_BY_EXAMPLE_COPY_5,
                DAVIDE,
                thirtyDaysAgo,
                tenDaysLater
        );

        loan.end(today);

        assertTrue(loan.isClosed());
        assertEquals(today, loan.getEndDate());
        assertFalse(Fixtures.TDD_BY_EXAMPLE_COPY_5.isReserved());
    }

    // fare in modo che il prestito sia rinnovato con limiti
}