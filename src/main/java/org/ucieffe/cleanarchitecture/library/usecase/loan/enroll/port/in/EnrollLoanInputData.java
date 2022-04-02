package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in;

import java.time.LocalDate;

public class EnrollLoanInputData {
    private String userId;
    private String isbn;
    private LocalDate startDate;

    public EnrollLoanInputData(String userId, String isbn, LocalDate startDate) {
        this.userId = userId;
        this.isbn = isbn;
        this.startDate = startDate;

    }

    public String getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
