package org.ucieffe.cleanarchitecture.library.entity;

import org.ucieffe.cleanarchitecture.library.Fixtures;

import java.time.LocalDate;
import java.util.UUID;

import static org.ucieffe.cleanarchitecture.library.Fixtures.*;

public class LoanBuilder {
    private UUID uuid;
    private BookItem bookItem;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dueDate;
    private LocalDate maximumDueDate;
    private Integer howManyRenewals;

    public LoanBuilder() {
        this.uuid = UUID.randomUUID();
        this.bookItem = Fixtures.TDD_BY_EXAMPLE_COPY_5;
        this.user = DAVIDE;
        this.startDate = THIRTY_DAYS_AGO;
        this.dueDate = TEN_DAYS_LATER;
        this.maximumDueDate = ONE_HUNDRED_DAYS_LATER;
        this.howManyRenewals = 0;
    }

    public Loan build() {
        return new Loan(uuid, bookItem, user, startDate, dueDate, maximumDueDate, howManyRenewals, endDate);
    }

    public LoanBuilder withUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public LoanBuilder withItem(BookItem bookItem) {
        this.bookItem = bookItem;
        return this;
    }

    public LoanBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public LoanBuilder withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LoanBuilder withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public LoanBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LoanBuilder withMaximumDueDate(LocalDate maximumDueDate) {
        this.maximumDueDate = maximumDueDate;
        return this;
    }

    public LoanBuilder withHowManyRenewals(Integer howManyRenewals) {
        this.howManyRenewals = howManyRenewals;
        return this;
    }
}
