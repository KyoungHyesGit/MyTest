package com.backend.mytest.controller;

import com.backend.mytest.dto.BookReqDTO;
import com.backend.mytest.dto.BookReqFormDTO;
import com.backend.mytest.dto.BookResDTO;
import com.backend.mytest.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookpage")
public class BookController {

    public final BookService bookService;

    @GetMapping("/index")
    public String gotoBookListPage(Model model){
        List<BookResDTO> booklist = bookService.getBookAllList();
        model.addAttribute("booklist",booklist);
        return "booklist";
    }

    @GetMapping("/add")
    public String gotoBookAddPage(BookReqDTO bookReqDTO){
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@Valid BookReqDTO bookReqDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-book";
        }
        bookService.createBook(bookReqDTO);
        return "redirect:/bookpage/index";
    }

    @GetMapping("/{id}")
    public String gotoBookPage(@PathVariable Long id, Model model){
        BookResDTO book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "book";
    }

    @GetMapping("/edit/{id}")
    public String gotoBookUpdatePage(@PathVariable Long id, Model model){
        BookResDTO book = bookService.getBookById(id);
        model.addAttribute("book",book);
        return "update-book";
    }


    @PostMapping("/edit/{id}")
    public String updateBook(@Valid @ModelAttribute("book") BookReqFormDTO book, BindingResult result, Model model, @PathVariable Long id){
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("errors", result.getAllErrors());

            return "update-book";
        }
        bookService.createBook(book);
        return "redirect:/bookpage/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return "redirect:/bookpage/index";
    }


}
