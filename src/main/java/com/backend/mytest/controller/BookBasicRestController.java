package com.backend.mytest.controller;

import com.backend.mytest.entity.BookEntity;
import com.backend.mytest.exception.BusinessException;
import com.backend.mytest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookBasicRestController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping
    public BookEntity createBook(@RequestBody BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    @GetMapping
    public List<BookEntity> getBookAllList(){
        return bookRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public BookEntity getBookById(@PathVariable Long id){
        return bookRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/isbn/{isbn}")
    public BookEntity getBookByIsbn(@PathVariable String isbn){
        return bookRepository.findByIsbn(isbn).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity newBookEntity){
        BookEntity orgBookEntity = bookRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
        orgBookEntity.setTitle((newBookEntity.getTitle()==null||newBookEntity.getTitle().trim()=="")?orgBookEntity.getTitle():newBookEntity.getTitle());
        orgBookEntity.setAuthor((newBookEntity.getAuthor()==null||newBookEntity.getAuthor().trim()=="")?orgBookEntity.getAuthor():newBookEntity.getAuthor());
        orgBookEntity.setGenre((newBookEntity.getGenre()==null||newBookEntity.getGenre().trim()=="")?orgBookEntity.getGenre():newBookEntity.getGenre());
        bookRepository.save(orgBookEntity);
        return orgBookEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id){
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
        bookRepository.delete(bookEntity);
        return ResponseEntity.ok("삭제 성공");
    }






}
