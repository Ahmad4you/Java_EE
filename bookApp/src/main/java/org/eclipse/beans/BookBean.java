package org.eclipse.beans;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

import org.eclipse.model.Book;
import org.eclipse.service.BookService;


@Named
@RequestScoped
public class BookBean {
    @Inject
    private BookService bookService;

    private Book book = new Book();

    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    public String addBook() {
        bookService.addBook(book);
        book = new Book(); // Reset the book
        return "index?faces-redirect=true";
    }

    public String updateBook() {
        bookService.updateBook(book);
        book = new Book(); // Reset the book
        return "index?faces-redirect=true";
    }

    public String deleteBook(Long id) {
        bookService.deleteBook(id);
        return "index?faces-redirect=true";
    }
    
    /*
     * einem JavaServer Faces (JSF) Kontext bedeutet, 
     * dass nach dem Löschen des Buches eine Weiterleitung zur Seite index.xhtml erfolgt. 
     * Der Parameter faces-redirect=true sorgt dafür, dass eine HTTP-Weiterleitung (Redirect) 
     * statt einer einfachen Navigation durchgeführt wird. Dies ist nützlich, 
     * um eine neue Anfrage zu starten und die URL im Browser zu aktualisieren.
     * 
     * 
     */

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
