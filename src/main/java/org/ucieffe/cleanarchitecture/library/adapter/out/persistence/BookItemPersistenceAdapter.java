package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.ucieffe.cleanarchitecture.library.entity.BookItem;
import org.ucieffe.cleanarchitecture.library.usecase.loan.enroll.port.out.GetAllBookItemsAvailable;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class BookItemPersistenceAdapter
        implements GetAllBookItemsAvailable {

    @Autowired
    private BookItemRepository bookItemRepository;

    @Override
    public List<BookItem> findAllBy(String isbn) {
        Example<BookItemJpaEntity> example =
                Example.of(BookItemJpaEntity.from(isbn, TRUE, FALSE));
        List<BookItemJpaEntity> bookItemsByIsbn =
                bookItemRepository.findAll(example);

        return BookItemMapper.mapToBookItemList(bookItemsByIsbn);
    }
}
