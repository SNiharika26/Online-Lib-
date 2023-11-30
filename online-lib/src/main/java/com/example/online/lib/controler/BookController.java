package com.example.online.lib.controler;

import com.example.online.lib.entity.Book;
import com.example.online.lib.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(bookId, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        try {
            bookService.deleteBook(bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/book/{id}")
    public String viewBookDetails(@PathVariable Long id, Model model) {
        logger.debug("Entering viewBookDetails method with ID: {}", id);

        try {
            Book book = bookService.getBookById(id);
            model.addAttribute("book", book);
            logger.debug("Book details retrieved: {}", book);

            return "bookDetails"; // Return the "book.html" template
        } catch (RuntimeException e) {
            logger.error("Error while fetching book details", e);
            return "errorPage"; // You can create an error page to handle exceptions
        }
    }


}
