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

    public Loan(UUID uuid, Item item, User user, LocalDate startDate, LocalDate endDate, LocalDate dueDate) {
        this.uuid = uuid;
        this.item = item;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
    }

    public Loan(UUID uuid, Item item, User user, LocalDate startDate) {
        this(uuid, item, user, startDate, null, null);
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
}
