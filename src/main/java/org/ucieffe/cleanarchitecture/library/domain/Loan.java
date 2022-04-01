package org.ucieffe.cleanarchitecture.library.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private UUID uuid;
    private Item item;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dueDate;

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate dueDate, LocalDate endDate) {
        this.uuid = uuid;
        this.item = item;
        this.user = user;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.endDate = endDate;
    }

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate dueDate) {
        this(uuid, item, user, startDate, dueDate, null);
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

    public boolean isClosed() {
        return endDate != null;
    }

    public void end(LocalDate endDate) {
        this.endDate = endDate;
        this.item.release();
    }

    public void renew(Integer days) {
        this.dueDate = dueDate.plusDays(days);
    }
}
