package org.ucieffe.cleanarchitecture.library.adapter.out.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BookItemJpaEntity {

    @Id
    private String id;
    private String isbn;
    private Boolean isCatalogued;
    private Boolean isReserved;

    public BookItemJpaEntity() {
    }

    public static BookItemJpaEntity from(String isbn,
                                         Boolean isCatalogued,
                                         Boolean isReserved) {
        BookItemJpaEntity bookItem = new BookItemJpaEntity();
        bookItem.setIsbn(isbn);
        bookItem.setCatalogued(isCatalogued);
        bookItem.setReserved(isReserved);
        return bookItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getCatalogued() {
        return isCatalogued;
    }

    public void setCatalogued(Boolean catalogued) {
        isCatalogued = catalogued;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }
}
