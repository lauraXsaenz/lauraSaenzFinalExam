package com.example.laurasaenzfinalexam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class items {
    private String Catcode;
    @Id
    private String Icode;
    private String Idesc;
    private double Price;
}
