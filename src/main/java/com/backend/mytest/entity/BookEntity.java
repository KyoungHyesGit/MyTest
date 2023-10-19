package com.backend.mytest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@DynamicUpdate
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(unique = true, nullable = false)
    private  String isbn;
    @Column
    private String genre;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

}
