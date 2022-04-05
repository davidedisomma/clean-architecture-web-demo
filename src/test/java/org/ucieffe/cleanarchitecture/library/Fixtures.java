package org.ucieffe.cleanarchitecture.library;

import org.ucieffe.cleanarchitecture.library.entity.BookItem;
import org.ucieffe.cleanarchitecture.library.entity.User;

import java.time.LocalDate;

import static java.lang.Boolean.TRUE;

public class Fixtures {
    public static final String DAVIDE_USER_ID = "12345";
    public static final User DAVIDE = new User(DAVIDE_USER_ID, "Davide", "Di Somma",
            LocalDate.of(1971, 8, 25), false);
    public static final String A_SUSPENDED_USER_ID = "66666";
    public static final User A_SUSPENDED_USER = new User(A_SUSPENDED_USER_ID, "Louis", "Cyfer",
            LocalDate.of(1966, 6, 6), true);

    public static final String TDD_BY_EXAMPLE_ISBN = "978-0321146533";
    public static final BookItem TDD_BY_EXAMPLE_COPY_2 = new BookItem("book-3456", TDD_BY_EXAMPLE_ISBN, TRUE, TRUE);
    public static final BookItem TDD_BY_EXAMPLE_COPY_5 = new BookItem("book-4567", TDD_BY_EXAMPLE_ISBN, TRUE, TRUE);

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate THIRTY_DAYS_AGO = TODAY.minusDays(30);
    public static final LocalDate TEN_DAYS_LATER = TODAY.plusDays(10);
    public static final LocalDate TWENTY_DAYS_LATER = TODAY.plusDays(20);
    public static final LocalDate THIRTY_DAYS_LATER = TODAY.plusDays(30);
    public static final LocalDate FORTY_DAYS_LATER = TODAY.plusDays(40);
    public static final LocalDate ONE_HUNDRED_DAYS_LATER = TODAY.plusDays(100);

}
