package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.in;

import org.junit.jupiter.api.Test;
import org.ucieffe.cleanarchitecture.library.usecase.InputValidationException;


import static org.junit.jupiter.api.Assertions.*;
import static org.ucieffe.cleanarchitecture.library.Fixtures.*;

class EnrollLoanInputDataTest {

    @Test
    void user_id_is_invalid_when_not_numeric() {
        try {
            new EnrollLoanInputData("noNumericUserId", TDD_BY_EXAMPLE_ISBN, TODAY);
            fail();
        } catch (InputValidationException e) {
            assertEquals("Invalid user id: noNumericUserId", e.getMessage());
        }
    }

    @Test
    void isbn_is_invalid_when_is_not_complaint_to_format() {
        try {
            new EnrollLoanInputData(DAVIDE_USER_ID, "12-NO-ISBN-CODE", TODAY);
            fail();
        } catch (InputValidationException e) {
            assertEquals("Invalid ISBN: 12-NO-ISBN-CODE", e.getMessage());
        }
    }

    @Test
    void start_date_is_invalid_when_is_in_the_future() {
        try {
            new EnrollLoanInputData(DAVIDE_USER_ID, TDD_BY_EXAMPLE_ISBN, TEN_DAYS_LATER);
            fail();
        } catch (InputValidationException e) {
            assertEquals("Invalid start date: " + TEN_DAYS_LATER, e.getMessage());
        }
    }
}