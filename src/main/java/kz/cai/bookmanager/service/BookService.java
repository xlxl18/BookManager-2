package kz.cai.bookmanager.service;

import kz.cai.bookmanager.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BookService {
    void addBook(Book book);
    void updateBook(Book book);
    void removeBook(int id);
    Book getBookById(int id);
    Page<Book> listBooks(Pageable pageable);
}
