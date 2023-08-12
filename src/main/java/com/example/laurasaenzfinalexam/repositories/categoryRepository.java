package com.example.laurasaenzfinalexam.repositories;

import com.example.laurasaenzfinalexam.entities.category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface categoryRepository extends JpaRepository<category, String> {


}
