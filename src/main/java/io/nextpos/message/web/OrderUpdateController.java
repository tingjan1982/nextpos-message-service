package io.nextpos.message.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class OrderUpdateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderUpdateController.class);

    private final SimpMessagingTemplate messagingTemplate;

    public OrderUpdateController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/inflightOrders/{clientId}")
    public String handleInflightOrdersUpdate(@PathVariable String clientId) {

        LOGGER.info("Sending inflight orders update message for client {}", clientId);

        messagingTemplate.convertAndSend("/topic/inflightOrders/" + clientId, clientId + ".inflightOrders.ordersChanged");

        return clientId;
    }

    @PostMapping("/orders/{orderId}")
    public String handleOrderUpdate(@PathVariable String orderId) {

        LOGGER.info("Sending order update message for order {}", orderId);

        messagingTemplate.convertAndSend("/topic/order/" + orderId, orderId + ".order.orderChanged");

        return orderId;
    }
}
