package com.jasencio.spring.jpa.h2.service;

import com.jasencio.spring.jpa.h2.model.Book;
import com.jasencio.spring.jpa.h2.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
   List<Book> getAllBooks(BookRepository bookRepository);
   Optional<Book> getBookById(BookRepository bookRepository, long id);
   Optional<Book> createBook(BookRepository bookRepository, Book book);
}
