package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in;

import org.ucieffe.cleanarchitecture.library.usecase.InputValidationException;

import java.time.LocalDate;

public class EnrollLoanInputData {
    private static final String ISBN_FORMAT =
            "^[0-9]{3}[-]{1}[0-9]{10}$";
    private String userId;
    private String isbn;
    private LocalDate startDate;

    public EnrollLoanInputData(String userId,
                               String isbn,
                               LocalDate startDate) {
        this.userId = userId;
        this.isbn = isbn;
        this.startDate = startDate;
        requireNumeric(userId);
        requireIsbn(isbn);
        requireNotInTheFuture(startDate);
    }

    private void requireNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InputValidationException("Invalid user id: " + value);
        }
    }

    private void requireIsbn(String isbn) {
        if(! isbn.matches(ISBN_FORMAT))
            throw new InputValidationException("Invalid ISBN: " + isbn);
    }

    private void requireNotInTheFuture(LocalDate startDate) {
        if(LocalDate.now().isBefore(startDate))
            throw new InputValidationException("Invalid start date: " + startDate);
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
