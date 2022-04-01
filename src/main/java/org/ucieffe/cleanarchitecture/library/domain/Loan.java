package org.ucieffe.cleanarchitecture.library.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private final UUID uuid;
    private final Item item;
    private final User user;
    private final LocalDate startDate;
    private final LocalDate maximumLoanDate;
    private LocalDate dueDate;
    private LocalDate endDate;

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate dueDate, LocalDate maximumLoanDate, LocalDate endDate) {
        this.uuid = uuid;
        this.item = item;
        this.user = user;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.maximumLoanDate = maximumLoanDate;
        this.endDate = endDate;
    }

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate dueDate, LocalDate maximumLoanDate) {
        this(uuid, item, user, startDate, dueDate, maximumLoanDate, null);
    }

    public boolean isClosed() {
        return endDate != null;
    }

    public void end(LocalDate endDate) {
        this.endDate = endDate;
        this.item.release();
    }

    public void renew(Integer days) {
        final LocalDate renewedDueDate = this.dueDate.plusDays(days);
        if (renewedDueDate.isBefore(maximumLoanDate))
            this.dueDate = renewedDueDate;
        else
            this.dueDate = maximumLoanDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getMaximumLoanDate() {
        return maximumLoanDate;
    }

}
