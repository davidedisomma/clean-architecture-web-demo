package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out;

import org.ucieffe.cleanarchitecture.library.entity.BookItem;

import java.util.List;

public interface GetAllBookItemsAvailable {
    List<BookItem> findAllBy(String isbn);
}
