package com.example.online.lib.service;

import com.example.online.lib.entity.Book;
import com.example.online.lib.repo.BookRepo;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public List<Book> getAllBooks()
    {
        return bookRepo.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }
//public Book getBookById(Long bookId) {
//    logger.info("Fetching book details for ID: " + bookId);
//    Book book = bookRepo.findById(bookId)
//            .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));
//    logger.info("Book details retrieved: " + book);
//    return book;
//}
    public Book addBook(Book book)
    {
        return bookRepo.save(book);
    }
    public Book updateBook(Long bookId,Book updateBook)
    {
        Book existingBook=getBookById(bookId);
        existingBook.setTitle(updateBook.getTitle());
        existingBook.setAuthor(updateBook.getAuthor());
        existingBook.setPrice(updateBook.getPrice());
        return bookRepo.save(existingBook);
    }
    public void deleteBook(Long bookId)
    {
        Book existingBook = getBookById(bookId);
        bookRepo.delete(existingBook);
    }
}
