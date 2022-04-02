package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in;

import java.time.LocalDate;

public class EnrollLoanOutputData {
    private String loanId;
    private LocalDate dueDate;

    public EnrollLoanOutputData(String loanId, LocalDate dueDate) {
        this.loanId = loanId;
        this.dueDate = dueDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
