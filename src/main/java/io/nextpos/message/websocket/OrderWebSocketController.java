package io.nextpos.message.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * Reference on WebSocket
 *
 * https://spring.io/guides/gs/messaging-stomp-websocket/
 * https://docs.spring.io/autorepo/docs/spring-security/4.0.x/reference/html/websocket.html
 * https://www.toptal.com/java/stomp-spring-boot-websocket
 */
@RestController
public class OrderWebSocketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWebSocketController.class);

    @MessageMapping("/inflightOrders/{clientId}")
    @SendTo("/topic/inflightOrders/{clientId}")
    public String inflightOrders(@DestinationVariable String clientId) {
        LOGGER.info("Received inflight orders message subscribe request for client id: {}", clientId);

        return clientId + ".inflightOrders.established";
    }

    @MessageMapping("/order/{orderId}")
    @SendTo("/topic/order/{orderId}")
    public String orderDetails(@DestinationVariable String orderId) {
        LOGGER.info("Received order message subscribe request for order id: {}", orderId);

        return orderId + ".order.established";
    }
}
