package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.ucieffe.cleanarchitecture.library.entity.BookItem;

import java.util.List;
import java.util.stream.Collectors;

public class BookItemMapper {
    public static BookItem mapToBookItem(BookItemJpaEntity bookItemJpa) {
        return new BookItem(
                bookItemJpa.getId(),
                bookItemJpa.getIsbn(),
                bookItemJpa.getCatalogued(),
                bookItemJpa.getReserved()
                );
    }

    public static List<BookItem> mapToBookItemList(
            List<BookItemJpaEntity> bookItemJpaList) {
        return bookItemJpaList.stream()
                .map(BookItemMapper::mapToBookItem)
                .collect(Collectors.toUnmodifiableList());
    }
}
