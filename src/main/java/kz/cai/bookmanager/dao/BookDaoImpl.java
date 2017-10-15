package kz.cai.bookmanager.dao;

import kz.cai.bookmanager.model.Book;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book successfully saved");
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book  successfully updated;");
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
        Session session =this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully loaded. Book details: " + book);

        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Book> listBooks(Pageable pageable) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaQuery cq = session.getCriteriaBuilder().createQuery(Book.class);
        cq.from(Book.class);
        Query query = session.createQuery(cq);

        ScrollableResults resultScroll = query.scroll(ScrollMode.FORWARD_ONLY);
        resultScroll.first();
        resultScroll.scroll((int) pageable.getOffset());
        List<Book> bookList = new ArrayList<>();
        int i = 0;
        while (pageable.getPageSize() > i++) {
        bookList.add((Book) resultScroll.get(0));
            if (!resultScroll.next())
                break;
        }
        resultScroll.last();
        long total = resultScroll.getRowNumber()+1;
        PageImpl<Book> page = new PageImpl<Book>(bookList,pageable, total);
        return page;
//        Session session = this.sessionFactory.getCurrentSession();
//        List<Book> bookList = session.createQuery("from Book").list();
//
//
//        for(Object book: bookList) {
//            logger.info("Book list: "+ book);
//        }
 //
        //  return bookList;
   }
}
