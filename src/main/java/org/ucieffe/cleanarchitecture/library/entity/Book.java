package org.ucieffe.cleanarchitecture.library.entity;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publishing;
    private Integer year;

    public Book(String isbn, String title, String author, String publishing, Integer year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishing = publishing;
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishing() {
        return publishing;
    }

    public Integer getYear() {
        return year;
    }
}
