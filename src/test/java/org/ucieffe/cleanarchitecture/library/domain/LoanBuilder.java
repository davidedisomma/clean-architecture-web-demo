package org.ucieffe.cleanarchitecture.library.domain;

import org.ucieffe.cleanarchitecture.library.Fixtures;

import java.time.LocalDate;
import java.util.UUID;

import static org.ucieffe.cleanarchitecture.library.Fixtures.*;

public class LoanBuilder {
    private UUID uuid;
    private Item item;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dueDate;

    public LoanBuilder() {
        this.uuid = UUID.randomUUID();
        this.item = Fixtures.TDD_BY_EXAMPLE_COPY_5;
        this.user = DAVIDE;
        this.startDate = thirtyDaysAgo;
        this.dueDate = tenDaysLater;
    }

    public Loan build() {
        return new Loan(uuid, item, user, startDate, dueDate, endDate);
    }

    public LoanBuilder withUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public LoanBuilder withItem(Item item) {
        this.item = item;
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
}
