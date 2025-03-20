package com.jasencio.spring.jpa.h2.service;

import com.jasencio.spring.jpa.h2.model.Book;
import com.jasencio.spring.jpa.h2.repository.BookRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
   @Override
   public List<Book> getAllBooks( BookRepository bookRepository) {
      List<Book> books = new ArrayList<Book>();
      bookRepository.findAll().forEach(books::add);
      return books;
   }

   @Override
   public Optional<Book> getBookById(BookRepository bookRepository, long id) {
      return bookRepository.findById(id);
   }

   @Override
   public Optional<Book> createBook(BookRepository bookRepository, Book book){
      return Optional.of(bookRepository.save(new Book(book.getTitle(), book.getDescription(), true)));
   }
}
