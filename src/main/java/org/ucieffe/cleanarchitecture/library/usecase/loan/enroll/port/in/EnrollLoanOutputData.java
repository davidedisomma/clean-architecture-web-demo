package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in;

import java.time.LocalDate;

import static org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.LoanOutcome.LOAN_SUCCESS;
import static org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.LoanOutcome.USER_SUSPENDED;

public class EnrollLoanOutputData {
    private LoanOutcome loanOutcome;
    private String loanId;
    private LocalDate dueDate;

    private EnrollLoanOutputData(LoanOutcome loanOutcome, String loanId, LocalDate dueDate) {
        this.loanOutcome = loanOutcome;
        this.loanId = loanId;
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

    public static EnrollLoanOutputData loanSuccessful(String loanId, LocalDate dueDate) {
        return new EnrollLoanOutputData(LOAN_SUCCESS, loanId, dueDate);
    }

    public static EnrollLoanOutputData userSuspended() {
        return new EnrollLoanOutputData(USER_SUSPENDED, null, null);
    }
}
