package org.ucieffe.cleanarchitecture.library.domain;

public class Item {
    private String id;
    private String isbn;
    private Boolean isCatalogued;
    private Boolean isReserved;

    public Item(String id, String isbn, Boolean isCatalogued, Boolean isReserved) {
        this.id = id;
        this.isbn = isbn;
        this.isCatalogued = isCatalogued;
        this.isReserved = isReserved;
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Boolean getCatalogued() {
        return isCatalogued;
    }

    public Boolean getReserved() {
        return isReserved;
    }
}
