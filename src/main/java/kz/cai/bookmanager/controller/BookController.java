package kz.cai.bookmanager.controller;
import kz.cai.bookmanager.model.Book;
import kz.cai.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookservice(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model, HttpServletRequest request) {
        Pageable pageable = createPageable(request);
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks(pageable));
        return "books";
    }

    private PageRequest createPageable(HttpServletRequest request) {
        String pageStr = request.getParameter("page");
        int page = pageStr != null ? Integer.parseInt(pageStr) : 0;
        String sizeStr = request.getParameter("size");
        int size = sizeStr != null ? Integer.parseInt(sizeStr) : 10;
        return PageRequest.of(page, size);
    }

    @RequestMapping(value="/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if(book.getId()==0) {
            this.bookService.addBook(book);
        }
        else {
            this.bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.removeBook(id);
        return "redirect:/books";
    }
    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBooks(createPageable(request)));
        return "books";
    }
    @RequestMapping("bookdata/{id}")

    public  String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        return "bookdata";
    }
}
