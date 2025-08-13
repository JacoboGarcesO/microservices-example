package org.example.cartservice.service;

import org.example.cartservice.dto.CartDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartOrdersPublisher {
   private final RabbitTemplate rabbitTemplate;

   public CartOrdersPublisher(RabbitTemplate rabbitTemplate) {
     this.rabbitTemplate = rabbitTemplate;
   }

   public void publishCartCreatedEvent(CartDTO cart) {
     rabbitTemplate.convertAndSend("cart-exchange", "cart-routing-key", cart);
     System.out.println("Cart created event published");
   }
}
