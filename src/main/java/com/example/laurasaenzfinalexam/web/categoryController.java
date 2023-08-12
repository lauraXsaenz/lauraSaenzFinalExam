package com.example.laurasaenzfinalexam.web;


import com.example.laurasaenzfinalexam.entities.category;
import com.example.laurasaenzfinalexam.entities.items;
import com.example.laurasaenzfinalexam.entities.sales;
import com.example.laurasaenzfinalexam.repositories.categoryRepository;
import com.example.laurasaenzfinalexam.repositories.itemsRepository;
import com.example.laurasaenzfinalexam.repositories.salesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@AllArgsConstructor
public class categoryController {

    @Autowired
    private categoryRepository catRepository;
    @Autowired
    private itemsRepository itemRepository;
    @Autowired
    private salesRepository saleRepository;

    @GetMapping("/home")
    public String showHome(Model model) {
        List<items> itemList = itemRepository.findAll();
        model.addAttribute("itemList", itemList);

        return "home";
    }

    @PostMapping("/save")
    public String save(Model model,
        @RequestParam("recno") int recno,
        @RequestParam("icode") String icode,
        @RequestParam("qty") double qty,
        @RequestParam("dot") @DateTimeFormat(pattern="yyyy-MM-dd") Date dot,
                       @RequestParam("catcode") String catcode) {

        sales sale = new sales();
        sale.setRecno(recno);
        sale.setIcode(icode);
        sale.setQty(qty);
        sale.setDot(dot);

        saleRepository.save(sale);
        List<sales> salesList = saleRepository.findAll();
        Map<String, Double> totalSalesMap = calculateTotalSalesByCategory(catcode);

        List<category> categories = catRepository.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("totalSalesMap", totalSalesMap);
        model.addAttribute("salesList", salesList);
        return "redirect:/home";

    }



    private Map<String, Double> calculateTotalSalesByCategory(String catcode) {
        double totalSales = saleRepository.sumTotalSalesByCategory(catcode);

        Map<String, Double> totalSalesMap = new HashMap<>();
        totalSalesMap.put(catcode, totalSales);

        return totalSalesMap;
    }
}


