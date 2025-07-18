package com.monevia.bookstore.user_service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserDTO {
    private String name;
    private String email;
    private String address;
}