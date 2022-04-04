package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import org.ucieffe.cleanarchitecture.library.entity.Item;

import java.util.List;
import java.util.stream.Collectors;

public class BookItemMapper {
    public static Item mapToBookItem(BookItemJpaEntity bookItemJpa) {
        return new Item(
                bookItemJpa.getId(),
                bookItemJpa.getIsbn(),
                bookItemJpa.getCatalogued(),
                bookItemJpa.getReserved()
                );
    }

    public static List<Item> mapToBookItemList(List<BookItemJpaEntity> bookItemJpaList) {
        return bookItemJpaList.stream()
                .map(bookItemJpa -> BookItemMapper.mapToBookItem(bookItemJpa))
                .collect(Collectors.toUnmodifiableList());
    }
}
