package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ucieffe.cleanarchitecture.library.entity.BookItem;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookItemPersistenceAdapterTest {

    @Autowired
    private BookItemPersistenceAdapter bookItemPersistenceAdapter;

    @Autowired
    private BookItemRepository bookItemRepository;

    @Test
    void find_all_book_by_isbn() {
        createBookItemWith("isbn_1", "book_1", TRUE, FALSE);
        createBookItemWith("isbn_1", "book_2", TRUE, FALSE);
        createBookItemWith("isbn_1", "book_3", FALSE, FALSE);
        createBookItemWith("isbn_1", "book_4", TRUE, TRUE);
        createBookItemWith("isbn_2", "book_10", TRUE, FALSE);

        List<BookItem> bookItems = bookItemPersistenceAdapter.findAllBy("isbn_1");

        assertEquals(2, bookItems.size());
        assertTrue(
                bookItems.stream()
                        .allMatch(item -> item.getIsbn().equals("isbn_1"))
        );
    }

    private BookItemJpaEntity createBookItemWith(String isbn, String itemId, Boolean catalogued, Boolean reserved) {
        BookItemJpaEntity bookItemJpaEntity = new BookItemJpaEntity();
        bookItemJpaEntity.setId(itemId);
        bookItemJpaEntity.setIsbn(isbn);
        bookItemJpaEntity.setCatalogued(catalogued);
        bookItemJpaEntity.setReserved(reserved);
        bookItemRepository.saveAndFlush(bookItemJpaEntity);
        return bookItemJpaEntity;
    }

}