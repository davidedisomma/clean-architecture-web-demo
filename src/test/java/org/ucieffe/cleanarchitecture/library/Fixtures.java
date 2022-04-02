package org.ucieffe.cleanarchitecture.library;

import org.ucieffe.cleanarchitecture.library.entity.Item;
import org.ucieffe.cleanarchitecture.library.entity.User;

import java.time.LocalDate;

import static java.lang.Boolean.TRUE;

public class Fixtures {
    public static final User DAVIDE = new User("user-12345", "Davide", "Di Somma",
            LocalDate.of(1971, 8, 25), false);

    public static final Item TDD_BY_EXAMPLE_COPY_2 = new Item("book-3456", "978-0321146533", TRUE, TRUE);
    public static final Item TDD_BY_EXAMPLE_COPY_5 = new Item("book-4567", "978-0321146533", TRUE, TRUE);

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate THIRTY_DAYS_AGO = TODAY.minusDays(30);
    public static final LocalDate TEN_DAYS_LATER = TODAY.plusDays(10);
    public static final LocalDate TWENTY_DAYS_LATER = TODAY.plusDays(20);
    public static final LocalDate THIRTY_DAYS_LATER = TODAY.plusDays(30);
    public static final LocalDate FORTY_DAYS_LATER = TODAY.plusDays(40);
    public static final LocalDate ONE_HUNDRED_DAYS_LATER = TODAY.plusDays(100);

}
