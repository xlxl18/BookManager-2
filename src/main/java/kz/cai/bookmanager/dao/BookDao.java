package kz.cai.bookmanager.dao;

import kz.cai.bookmanager.model.Book;

import java.util.List;

/**
 * Created by Тима on 04.10.2017.
 */
public interface BookDao {

    void addBook(Book book);
    void updateBook(Book book);
    void removeBook(int id);
    Book getBookById(int id);
    List<Book> listBooks();
}
