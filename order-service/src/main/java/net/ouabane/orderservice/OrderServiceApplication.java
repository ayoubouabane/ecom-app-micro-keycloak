package net.ouabane.orderservice;

import net.ouabane.orderservice.entities.Order;
import net.ouabane.orderservice.entities.OrderState;
import net.ouabane.orderservice.entities.ProductItem;
import net.ouabane.orderservice.repositories.OrderRepository;
import net.ouabane.orderservice.repositories.ProductItemRepository;
import net.ouabane.orderservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository,
            InventoryRestClient inventoryRestClient
    ){
        return args -> {
            //List<Product> allProducts = inventoryRestClient.getAllProducts();
            List<String> productsIds = List.of("P1","P2","P3","P4");
            for (int i = 0; i < 5; i++) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .date(LocalDate.now())
                        .state(OrderState.PENDING)
                        .build();
                Order savedOrder = orderRepository.save(order);
                productsIds.forEach(pId->{
                    ProductItem productItem = ProductItem.builder()
                            .productId(pId)
                            .quantity(new Random().nextInt(20))
                            .price(Math.random()*8000+10)
                            .order(savedOrder)
                            .build();
                    productItemRepository.save(productItem);
                });

            }
        };
    }
}
