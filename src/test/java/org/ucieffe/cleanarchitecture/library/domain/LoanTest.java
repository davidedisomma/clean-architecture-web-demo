package org.ucieffe.cleanarchitecture.library.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    void give_back_the_book() {
        final LocalDate today = LocalDate.now();
        final LocalDate thirtyDaysAgo = today.minusDays(30);
        final Item borrowedItem = new Item("book-4567", "978-0321146533", TRUE, TRUE);
        Loan loan = new Loan(
                UUID.randomUUID(),
                borrowedItem,
                new User("user-12345", "Davide", "Di Somma", LocalDate.of(1971, 8, 25), false),
                thirtyDaysAgo
        );

        loan.end(today);

        assertTrue(loan.isClosed());
        assertEquals(today, loan.getEndDate());
        assertFalse(borrowedItem.isReserved());
    }

    // fare in modo che il prestito sia rinnovato con limiti
}