package com.jasencio.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasencio.spring.jpa.h2.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByPublished(boolean published);

  List<Book> findByTitleContainingIgnoreCase(String title);
}
