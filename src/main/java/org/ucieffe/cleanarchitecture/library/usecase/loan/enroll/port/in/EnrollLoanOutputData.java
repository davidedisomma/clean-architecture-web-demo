package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in;

import org.ucieffe.cleanarchitecture.library.entity.Loan;

import java.time.LocalDate;

import static org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.LoanOutcome.*;

public class EnrollLoanOutputData {
    private LoanOutcome loanOutcome;
    private String loanId;
    private LocalDate dueDate;
    private String bookItemId;

    private EnrollLoanOutputData(LoanOutcome loanOutcome, String loanId, String bookItemId, LocalDate dueDate) {
        this.loanOutcome = loanOutcome;
        this.loanId = loanId;
        this.bookItemId = bookItemId;
        this.dueDate = dueDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LoanOutcome getOutcome() {
        return loanOutcome;
    }

    public String getBookItemId() {
        return bookItemId;
    }

    public static EnrollLoanOutputData loanSuccessful(Loan loan) {
        return new EnrollLoanOutputData(LOAN_SUCCESS, loan.getUuid().toString(), loan.getItem().getId(), loan.getDueDate());
    }

    public static EnrollLoanOutputData userSuspended() {
        return new EnrollLoanOutputData(USER_SUSPENDED, null, null, null);
    }
    public static EnrollLoanOutputData bookItemNotAvailable() {
        return new EnrollLoanOutputData(BOOK_ITEM_NOT_AVAILABLE, null, null, null);
    }
}
