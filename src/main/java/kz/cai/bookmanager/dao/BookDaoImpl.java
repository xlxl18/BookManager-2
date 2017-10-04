package kz.cai.bookmanager.dao;

import kz.cai.bookmanager.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.slf4j.Logger;

@Repository
public class BookDaoImpl implements BookDao {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        //получаем текущую сессию
        Session session = this.sessionFactory.getCurrentSession();
        //сохраняем объект
        session.persist(book);
        //Добавить инфу в логгирование
        logger.info("Book successfully saved");
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book swa successfully updated;");
    }

    @Override
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));

        if(book!=null) {
            session.delete(book);

        }
        logger.info("Book was successfully deleted");
    }

    @Override
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully loaded");
        return book;
    }

    @Override
    public List<Book> listBooks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book").list();

        for(Book book: bookList) {
            logger.info("Book list: "+ bookList);
        }
        return bookList;
    }
}
