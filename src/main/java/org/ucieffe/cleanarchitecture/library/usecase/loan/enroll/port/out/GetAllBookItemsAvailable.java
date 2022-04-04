package org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out;

import org.ucieffe.cleanarchitecture.library.entity.Item;

import java.util.List;

public interface GetAllBookItemsAvailable {
    List<Item> findAllBy(String isbn);
}
