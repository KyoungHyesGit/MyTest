package com.backend.mytest.controller;


import com.backend.mytest.dto.BookReqDTO;
import com.backend.mytest.dto.BookResDTO;
import com.backend.mytest.entity.BookEntity;
import com.backend.mytest.exception.BusinessException;
import com.backend.mytest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {

    public final BookService bookService;

    @PostMapping
    public BookResDTO createBook(@RequestBody BookReqDTO bookReqDTO){
        System.out.println(bookReqDTO.getAuthor());
        return bookService.createBook(bookReqDTO);
    }

    @GetMapping
    public List<BookResDTO> getBookAllList(){
        return bookService.getBookAllList();
    }

    @GetMapping(value = "/{id}")
    public BookResDTO getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping(value = "/isbn/{isbn}")
    public BookResDTO getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn);
    }

    @PutMapping(value = "/{id}")
    public BookResDTO updateBook(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO){

        return bookService.updateBook(id, bookReqDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok("삭제 성공");
    }

}
