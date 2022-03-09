package com.example.res.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data
public class Food {

    private String name;
    private String id;
    private Integer price;
    private String expireDate;
    private Integer quant;
}
