package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.ucieffe.cleanarchitecture.library.Fixtures;
import org.ucieffe.cleanarchitecture.library.entity.Book;
import org.ucieffe.cleanarchitecture.library.entity.Loan;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.EnrollLoanInputData;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.EnrollLoanOutputData;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.GetAllBookItemsAvailable;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.GetUserDetails;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.PersistLoan;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.ucieffe.cleanarchitecture.library.Fixtures.*;

class EnrollLoanUseCaseTest {

    private UUID anyUUID = UUID.randomUUID();
    private GetUserDetails getUserDetails;
    private GetAllBookItemsAvailable getAllBookItemsAvailable;
    private UUIDGenerator uuidGenerator;
    private PersistLoan persistLoan;
    private EnrollLoanUseCase enrollLoanUseCase;

    @BeforeEach
    void setUp() {
        getUserDetails = mock(GetUserDetails.class);
        getAllBookItemsAvailable = mock(GetAllBookItemsAvailable.class);
        uuidGenerator = mock(UUIDGenerator.class);
        persistLoan = mock(PersistLoan.class);
        enrollLoanUseCase = new EnrollLoanUseCase(getUserDetails, getAllBookItemsAvailable, persistLoan, uuidGenerator);
    }

    @Test
    void return_output_when_enroll_loan() {
        when(getUserDetails.findBy("user-12345"))
                .thenReturn(Fixtures.DAVIDE);
        when(getAllBookItemsAvailable.findBy("isbn999"))
                .thenReturn(List.of(TDD_BY_EXAMPLE_COPY_2, TDD_BY_EXAMPLE_COPY_5));
        when(uuidGenerator.uuid()).thenReturn(anyUUID);

        EnrollLoanInputData inputData = new EnrollLoanInputData("user-12345", "isbn999", today);

        EnrollLoanOutputData outputData = enrollLoanUseCase.execute(inputData);

        assertEquals(anyUUID.toString(), outputData.getLoanId());
        assertEquals(thirtyDaysLater, outputData.getDueDate());
    }

    @Test
    void save_loan_during_enrollment() {
        when(getUserDetails.findBy("user-12345"))
                .thenReturn(Fixtures.DAVIDE);
        when(getAllBookItemsAvailable.findBy("isbn999"))
                .thenReturn(List.of(TDD_BY_EXAMPLE_COPY_2, TDD_BY_EXAMPLE_COPY_5));
        when(uuidGenerator.uuid()).thenReturn(anyUUID);

        EnrollLoanInputData inputData = new EnrollLoanInputData("user-12345", "isbn999", today);

        enrollLoanUseCase.execute(inputData);

        ArgumentCaptor<Loan> loanArgumentCaptor = ArgumentCaptor.forClass(Loan.class);
        verify(persistLoan).persist(loanArgumentCaptor.capture());
        Loan persistedLoan = loanArgumentCaptor.getValue();
        assertEquals(anyUUID, persistedLoan.getUuid());
        assertEquals(DAVIDE, persistedLoan.getUser());
        assertEquals(TDD_BY_EXAMPLE_COPY_2, persistedLoan.getItem());
        assertEquals(today, persistedLoan.getStartDate());
        assertEquals(thirtyDaysLater, persistedLoan.getDueDate());
        assertEquals(oneHundredDaysLater, persistedLoan.getMaximumDueDate());
        assertEquals(0, persistedLoan.getHowManyRenewals());
    }
}