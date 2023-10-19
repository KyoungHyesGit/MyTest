package com.backend.mytest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookReqDTO {
    @NotEmpty(message = "title은 필수 입력 항목입니다")   // 공백 허용
    private String title;
    @NotEmpty(message = "author는 필수 입력 항목입니다")   // 공백 허용
    private String author;
    @NotEmpty(message = "isbn은 필수 입력 항목입니다")   // 공백 허용
    private  String isbn;
    private String genre;

}
