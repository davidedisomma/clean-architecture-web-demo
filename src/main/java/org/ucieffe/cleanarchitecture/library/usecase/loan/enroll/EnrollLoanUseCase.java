package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll;

import org.ucieffe.cleanarchitecture.library.entity.Item;
import org.ucieffe.cleanarchitecture.library.entity.Loan;
import org.ucieffe.cleanarchitecture.library.entity.User;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.EnrollLoanInputBoundary;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.EnrollLoanInputData;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.EnrollLoanOutputData;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.GetAllBookItemsAvailable;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.GetUserDetails;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.PersistLoan;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class EnrollLoanUseCase implements EnrollLoanInputBoundary {

    private final GetUserDetails getUserDetails;
    private final GetAllBookItemsAvailable getAllBookItemsAvailable;
    private final PersistLoan persistLoan;
    private final UUIDGenerator uuidGenerator;

    public EnrollLoanUseCase(GetUserDetails getUserDetails,
                             GetAllBookItemsAvailable getAllBookItemsAvailable,
                             PersistLoan persistLoan,
                             UUIDGenerator uuidGenerator) {

        this.getUserDetails = getUserDetails;
        this.getAllBookItemsAvailable = getAllBookItemsAvailable;
        this.persistLoan = persistLoan;
        this.uuidGenerator = uuidGenerator;
    }

    @Override
    public EnrollLoanOutputData execute(EnrollLoanInputData enrollLoanInputData) {
        User userToLoan = getUserDetails.findBy(enrollLoanInputData.getUserId());
        final List<Item> availableBooks = getAllBookItemsAvailable.findBy(enrollLoanInputData.getIsbn());
        UUID loanId = uuidGenerator.uuid();
        final LocalDate startDate = enrollLoanInputData.getStartDate();
        LocalDate dueDate = startDate.plusDays(30);
        LocalDate maximumDueDate = startDate.plusDays(100);
        Loan loan = new Loan(loanId, availableBooks.get(0), userToLoan,
                startDate, dueDate, maximumDueDate);
        persistLoan.persist(loan);

        return new EnrollLoanOutputData(loanId.toString(), dueDate);
    }
}
