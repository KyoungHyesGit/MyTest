package com.backend.mytest.repository;

import com.backend.mytest.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository  extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn(String isbn);
}
