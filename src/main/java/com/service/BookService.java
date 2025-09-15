package com.bookvault.service;

import com.bookvault.model.Book;
import com.bookvault.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    // Add a new book
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    // Get books by author
    public List<Book> getBooksByAuthor(String author) {
        return bookRepo.findByAuthor(author);
    }

    // Get books by genre
    public List<Book> getBooksByGenre(String genre) {
        return bookRepo.findByGenre(genre);
    }

    // Get book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    // Update book
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepo.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setGenre(updatedBook.getGenre());
            book.setRating(updatedBook.getRating());
            return bookRepo.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // Delete book
    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
