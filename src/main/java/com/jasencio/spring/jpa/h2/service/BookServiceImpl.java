package com.jasencio.spring.jpa.h2.service;

import com.jasencio.spring.jpa.h2.model.Book;
import com.jasencio.spring.jpa.h2.repository.BookRepository;

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

   @Override
   public Book updateBook(BookRepository bookRepository, Book book, long id){
      Optional<Book> bookData = bookRepository.findById(id);
      if (bookData.isPresent()) {
         Book DataBook = bookData.get();
         DataBook.setTitle(book.getTitle());
         DataBook.setDescription(book.getDescription());
         DataBook.setPublished(book.isPublished());
         bookRepository.save(DataBook);
         return DataBook;
      } else {
         return null;
      }
   }

   @Override
   public Optional<Book> deleteBook(BookRepository bookRepository, long id){
      Optional<Book> book = bookRepository.findById(id);
      if (book.isPresent()) {
         bookRepository.deleteById(id);
      }
      return Optional.empty();
   }

   @Override
   public Optional<Book> deleteAll(BookRepository bookRepository){
      bookRepository.deleteAll();
      return Optional.empty();
   }

}
