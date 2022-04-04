package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.ucieffe.cleanarchitecture.library.Fixtures;
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
import static org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in.LoanOutcome.*;

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

        when(getUserDetails.findBy(DAVIDE_USER_ID))
                .thenReturn(Fixtures.DAVIDE);
        when(getAllBookItemsAvailable.findAllBy(TDD_BY_EXAMPLE_ISBN))
                .thenReturn(List.of(TDD_BY_EXAMPLE_COPY_2, TDD_BY_EXAMPLE_COPY_5));
        when(uuidGenerator.uuid()).thenReturn(anyUUID);
    }

    @Test
    void return_output_when_enroll_loan() {
        when(getUserDetails.findBy(DAVIDE_USER_ID))
                .thenReturn(Fixtures.DAVIDE);
        when(getAllBookItemsAvailable.findAllBy(TDD_BY_EXAMPLE_ISBN))
                .thenReturn(List.of(TDD_BY_EXAMPLE_COPY_2, TDD_BY_EXAMPLE_COPY_5));
        when(uuidGenerator.uuid()).thenReturn(anyUUID);

        EnrollLoanInputData inputData = new EnrollLoanInputData(DAVIDE_USER_ID, TDD_BY_EXAMPLE_ISBN, TODAY);

        EnrollLoanOutputData outputData = enrollLoanUseCase.execute(inputData);

        assertEquals(LOAN_SUCCESS, outputData.getOutcome());
        assertEquals(anyUUID.toString(), outputData.getLoanId());
        assertEquals(TDD_BY_EXAMPLE_COPY_2.getId(), outputData.getBookItemId());
        assertEquals(THIRTY_DAYS_LATER, outputData.getDueDate());
    }

    @Test
    void save_loan_during_enrollment() {
        when(getUserDetails.findBy(DAVIDE_USER_ID))
                .thenReturn(Fixtures.DAVIDE);
        when(getAllBookItemsAvailable.findAllBy(TDD_BY_EXAMPLE_ISBN))
                .thenReturn(List.of(TDD_BY_EXAMPLE_COPY_2, TDD_BY_EXAMPLE_COPY_5));
        when(uuidGenerator.uuid()).thenReturn(anyUUID);

        EnrollLoanInputData inputData = new EnrollLoanInputData(DAVIDE_USER_ID, TDD_BY_EXAMPLE_ISBN, TODAY);

        enrollLoanUseCase.execute(inputData);

        ArgumentCaptor<Loan> loanArgumentCaptor = ArgumentCaptor.forClass(Loan.class);
        verify(persistLoan).persist(loanArgumentCaptor.capture());
        Loan persistedLoan = loanArgumentCaptor.getValue();
        assertEquals(anyUUID, persistedLoan.getUuid());
        assertEquals(DAVIDE, persistedLoan.getUser());
        assertEquals(TDD_BY_EXAMPLE_COPY_2, persistedLoan.getItem());
        assertEquals(TODAY, persistedLoan.getStartDate());
        assertEquals(THIRTY_DAYS_LATER, persistedLoan.getDueDate());
        assertEquals(ONE_HUNDRED_DAYS_LATER, persistedLoan.getMaximumDueDate());
        assertEquals(0, persistedLoan.getHowManyRenewals());
    }

    @Test
    void refute_loan_when_user_is_suspended() {
        when(getUserDetails.findBy(A_SUSPENDED_USER_ID))
                .thenReturn(Fixtures.A_SUSPENDED_USER);

        EnrollLoanInputData inputData = new EnrollLoanInputData(A_SUSPENDED_USER_ID, "000-0000000000", TODAY);

        EnrollLoanOutputData outputData = enrollLoanUseCase.execute(inputData);

        assertEquals(USER_SUSPENDED, outputData.getOutcome());
    }

    @Test
    void refute_loan_when_book_item_is_not_available() {
        when(getUserDetails.findBy(DAVIDE_USER_ID))
                .thenReturn(DAVIDE);
        when(getAllBookItemsAvailable.findAllBy(TDD_BY_EXAMPLE_ISBN))
                .thenReturn(List.of());

        EnrollLoanInputData inputData = new EnrollLoanInputData(DAVIDE_USER_ID, TDD_BY_EXAMPLE_ISBN, TODAY);

        EnrollLoanOutputData outputData = enrollLoanUseCase.execute(inputData);

        assertEquals(BOOK_ITEM_NOT_AVAILABLE, outputData.getOutcome());
    }
}