package com.example.laurasaenzfinalexam.repositories;

import com.example.laurasaenzfinalexam.entities.sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface salesRepository extends JpaRepository<sales, Long> {

    @Query("SELECT COALESCE(SUM(s.qty), 0) FROM sales s WHERE s.icode = :catcode")
    double sumTotalSalesByCategory(String catcode);


}
