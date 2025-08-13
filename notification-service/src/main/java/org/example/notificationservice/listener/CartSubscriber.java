package org.example.notificationservice.listener;

import org.example.notificationservice.model.Cart;
import org.example.notificationservice.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CartSubscriber {
  private final EmailService emailService;

  public CartSubscriber(EmailService emailService) {
    this.emailService = emailService;
  }

  @RabbitListener(queues = "cart-queue")
  public void receiveCart(Cart cart) {
    emailService.sendEmail(cart.getUserEmail(), "Confirmación de compra", "Gracias por tu compra! " + cart.getProducts().size());
  }

}
