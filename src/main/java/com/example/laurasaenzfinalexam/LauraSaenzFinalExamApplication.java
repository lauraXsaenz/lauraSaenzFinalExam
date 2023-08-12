package com.example.laurasaenzfinalexam;

import com.example.laurasaenzfinalexam.entities.category;
import com.example.laurasaenzfinalexam.entities.items;
import com.example.laurasaenzfinalexam.repositories.categoryRepository;
import com.example.laurasaenzfinalexam.repositories.itemsRepository;
import com.example.laurasaenzfinalexam.repositories.salesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LauraSaenzFinalExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(LauraSaenzFinalExamApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(categoryRepository catRepository, itemsRepository itemRepository) {
		return args -> {

			if (catRepository.count() == 0) {
				catRepository.save(new category("101","School Supplies"));
				catRepository.save(new category("102","Drinks"));
				catRepository.save(new category("103","Dog Items"));

				catRepository.findAll().forEach(p -> {
					System.out.println(p.getCatdesc());
				});

			}
			if (itemRepository.count() == 0) {
				itemRepository.save(new items("102","J102A","coke",5.00));
				itemRepository.save(new items("101","J101B","Vines paper pad",4.00));
				itemRepository.save(new items("102","J102B","pepsi",4.00));
				itemRepository.save(new items("103","J103C","purina dog food",22.5));
				itemRepository.save(new items("101","J101C","xyz eraser",1.5));
				itemRepository.save(new items("103","J103D","dog chew toy",13.5));
				catRepository.findAll().forEach(p -> {
					System.out.println(p.getCatdesc());
				});

			}
			;
		};
	}

}
