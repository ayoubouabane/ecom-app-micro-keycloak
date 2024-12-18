package net.ouabane.inventoryservice;

import net.ouabane.inventoryservice.entities.Product;
import net.ouabane.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product.builder().id("P1").name("PC").price(8000).quantity(2).build());
            productRepository.save(Product.builder().id("P2").name("Phone").price(12000).quantity(5).build());
            productRepository.save(Product.builder().id("P3").name("Ecran").price(10000).quantity(4).build());
            productRepository.save(Product.builder().id("P4").name("Imprimante").price(5000).quantity(6).build());
        };
    }
    /*@Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            productRepository.save(Product
                    .builder().id(UUID.randomUUID().toString()).name("SMART_VR").price(12000).quantity(10)
                    .build());
            productRepository.save(Product
                    .builder().id(UUID.randomUUID().toString()).name("DISQUE_DURE").price(2000).quantity(30)
                    .build());
            productRepository.save(Product
                    .builder().id(UUID.randomUUID().toString()).name("SmartPhone").price(18000).quantity(20)
                    .build());
        };
    }*/
}
