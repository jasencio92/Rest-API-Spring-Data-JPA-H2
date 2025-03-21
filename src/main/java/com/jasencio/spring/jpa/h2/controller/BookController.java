package com.jasencio.spring.jpa.h2.controller;

import java.util.List;
import java.util.Optional;

import com.jasencio.spring.jpa.h2.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasencio.spring.jpa.h2.model.Book;
import com.jasencio.spring.jpa.h2.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {
   @Autowired
   BookRepository bookRepository;
   //poo
   @GetMapping("/books")
   public ResponseEntity<List<Book>> getAllBooks() {
      try {
         BookServiceImpl BookService = new BookServiceImpl();
         List<Book> books = BookService.getAllBooks(bookRepository);
         if (books.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
         return new ResponseEntity<>(books, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   //poo
   @GetMapping("/books/{id}")
   public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
      try {
         BookServiceImpl BookService = new BookServiceImpl();
         Optional<Book> book = BookService.getBookById(bookRepository, id);
         if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
         } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   //poo
   @PostMapping("/books")
   public ResponseEntity<Optional<Book>> createBook(@RequestBody Book book) {
      try {
         BookServiceImpl BookService = new BookServiceImpl();
         Optional<Book> newBook = BookService.createBook(bookRepository, book);
         return new ResponseEntity<>(newBook, HttpStatus.CREATED);
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   //poo
   @PutMapping("/books/{id}")
   public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
      try {
         Optional<Book> bookData = bookRepository.findById(id);
         BookServiceImpl BookService = new BookServiceImpl();
         Book DataBook = BookService.updateBook(bookRepository, book, id);
         if (DataBook == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         } else {
            return new ResponseEntity<>(bookRepository.save(DataBook), HttpStatus.OK);
         }
      } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   //poo
   @DeleteMapping("/books/{id}")
   public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
      try {
         BookServiceImpl BookService = new BookServiceImpl();
         BookService.deleteBook(bookRepository, id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   //poo
   @DeleteMapping("/books")
   public ResponseEntity<HttpStatus> deleteAllBooks() {
      try {
         BookServiceImpl BookService = new BookServiceImpl();
         BookService.deleteAll(bookRepository);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }
}
