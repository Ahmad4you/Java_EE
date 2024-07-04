package org.eclipse.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.model.Book;

@ApplicationScoped
public class BookService {
    private List<Book> books = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        book.setId(idGenerator.getAndIncrement());
        books.add(book);
    }

    public void updateBook(Book book) {
        books.replaceAll(b -> b.getId().equals(book.getId()) ? book : b);
    }

    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
