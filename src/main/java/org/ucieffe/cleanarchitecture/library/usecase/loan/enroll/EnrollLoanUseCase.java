package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll;

import org.ucieffe.cleanarchitecture.library.entity.BookItem;
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
        if(userToLoan.getSuspended())
            return EnrollLoanOutputData.userSuspended();
        final List<BookItem> availableBooks =
                getAllBookItemsAvailable.findAllBy(enrollLoanInputData.getIsbn());
        if(availableBooks.isEmpty())
            return EnrollLoanOutputData.bookItemNotAvailable();

        Loan loan = createLoan(enrollLoanInputData, userToLoan, availableBooks.get(0));
        persistLoan.persist(loan);

        return EnrollLoanOutputData.loanSuccessful(loan);
    }

    private Loan createLoan(EnrollLoanInputData enrollLoanInputData,
                            User userToLoan,
                            BookItem bookItemToLoan) {
        UUID loanId = uuidGenerator.uuid();
        final LocalDate startDate = enrollLoanInputData.getStartDate();
        LocalDate dueDate = startDate.plusDays(30);
        LocalDate maximumDueDate = startDate.plusDays(100);
        Loan loan = new Loan(loanId, bookItemToLoan, userToLoan,
                startDate, dueDate, maximumDueDate);
        return loan;
    }
}
