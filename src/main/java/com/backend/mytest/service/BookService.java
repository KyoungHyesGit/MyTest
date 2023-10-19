package com.backend.mytest.service;

import com.backend.mytest.dto.BookReqDTO;
import com.backend.mytest.dto.BookReqFormDTO;
import com.backend.mytest.dto.BookResDTO;
import com.backend.mytest.entity.BookEntity;
import com.backend.mytest.exception.BusinessException;
import com.backend.mytest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public BookResDTO createBook(BookReqDTO bookReqDTO){
        BookEntity bookEntity =  bookRepository.save(modelMapper.map(bookReqDTO,BookEntity.class));
        return modelMapper.map(bookEntity, BookResDTO.class);
    }

    public BookResDTO createBook(BookReqFormDTO bookReqFormDTO){
        BookEntity bookEntity =  bookRepository.save(modelMapper.map(bookReqFormDTO,BookEntity.class));
        return modelMapper.map(bookEntity, BookResDTO.class);
    }

    public List<BookResDTO> getBookAllList(){
        return bookRepository.findAll().stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookResDTO.class))
                .collect(Collectors.toList());
    }

    public BookResDTO getBookById(Long id){
        return modelMapper.map(bookRepository.findById(id), BookResDTO.class);
    }
    public BookResDTO getBookByIsbn(String isbn){
        return modelMapper.map(bookRepository.findByIsbn(isbn), BookResDTO.class);
    }

    public BookResDTO updateBook(Long id, BookReqDTO bookReqDTO){
        BookEntity orgBookEntity = bookRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
        orgBookEntity.setTitle((bookReqDTO.getTitle()==null||bookReqDTO.getTitle().trim()=="")?orgBookEntity.getTitle():bookReqDTO.getTitle());
        orgBookEntity.setAuthor((bookReqDTO.getAuthor()==null||bookReqDTO.getAuthor().trim()=="")?orgBookEntity.getAuthor():bookReqDTO.getAuthor());
        orgBookEntity.setGenre((bookReqDTO.getGenre()==null||bookReqDTO.getGenre().trim()=="")?orgBookEntity.getGenre():bookReqDTO.getGenre());

        return modelMapper.map(orgBookEntity, BookResDTO.class);
    }

    public void deleteBookById(Long id){
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(()->new BusinessException("검색 결과 없음", HttpStatus.NOT_FOUND));
        bookRepository.delete(bookEntity);
    }


}
