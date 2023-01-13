package com.example.demo.dto;

import lombok.*;

@Data // Getter & Setter & toString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;
}
