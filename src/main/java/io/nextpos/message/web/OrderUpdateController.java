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
    public void handleInflightOrdersUpdate(@PathVariable String clientId) {

        LOGGER.info("Sending inflight order update message for client {}", clientId);

        messagingTemplate.convertAndSend("/topic/inflightOrders/" + clientId, clientId + ".inflightOrders.ordersChanged");
    }
}
