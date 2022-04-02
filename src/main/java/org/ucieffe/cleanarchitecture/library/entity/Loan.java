package org.ucieffe.cleanarchitecture.library.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private final UUID uuid;
    private final Item item;
    private final User user;
    private final LocalDate startDate;
    private final LocalDate maximumDueDate;
    private Integer howManyRenewals;
    private LocalDate dueDate;
    private LocalDate endDate;

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate dueDate, LocalDate maximumDueDate, Integer howManyRenewals, LocalDate endDate) {
        this.uuid = uuid;
        this.item = item;
        this.user = user;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.maximumDueDate = maximumDueDate;
        this.howManyRenewals = howManyRenewals;
        this.endDate = endDate;
    }

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate dueDate, LocalDate maximumDueDate, Integer howManyRenewals) {
        this(uuid, item, user, startDate, dueDate, maximumDueDate, howManyRenewals, null);
    }

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate dueDate, LocalDate maximumDueDate) {
        this(uuid, item, user, startDate, dueDate, maximumDueDate, 0, null);
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
        if (isOvercomingMaximumDueDate(renewedDueDate))
            this.dueDate = renewedDueDate;
        else
            this.dueDate = maximumDueDate;
        incrementRenewalsNumber();
    }

    private boolean isOvercomingMaximumDueDate(LocalDate renewedDueDate) {
        return renewedDueDate.isBefore(maximumDueDate);
    }

    private void incrementRenewalsNumber() {
        ++howManyRenewals;
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

    public LocalDate getMaximumDueDate() {
        return maximumDueDate;
    }

    public Integer getHowManyRenewals() {
        return howManyRenewals;
    }
}
