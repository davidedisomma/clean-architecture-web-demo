package org.ucieffe.cleanarchitecture.library;

import org.ucieffe.cleanarchitecture.library.domain.Item;
import org.ucieffe.cleanarchitecture.library.domain.User;

import java.time.LocalDate;

import static java.lang.Boolean.TRUE;

public class Fixtures {
    public static final User DAVIDE = new User("user-12345", "Davide", "Di Somma",
            LocalDate.of(1971, 8, 25), false);

    public static final Item TDD_BY_EXAMPLE_COPY_5 = new Item("book-4567", "978-0321146533", TRUE, TRUE);

    public static final LocalDate today = LocalDate.now();
    public static final LocalDate thirtyDaysAgo = today.minusDays(30);
    public static final LocalDate tenDaysLater = today.plusDays(10);
    public static final LocalDate thirtyDaysLater = today.plusDays(30);
    public static final LocalDate fortyDaysLater = today.plusDays(40);

}
