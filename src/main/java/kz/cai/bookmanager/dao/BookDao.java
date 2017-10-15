package kz.cai.bookmanager.dao;

import kz.cai.bookmanager.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ���� on 04.10.2017.
 */
public interface BookDao {

    void addBook(Book book);
    void updateBook(Book book);
    void removeBook(int id);
    Book getBookById(int id);
    Page<Book> listBooks(Pageable pageable);
}
