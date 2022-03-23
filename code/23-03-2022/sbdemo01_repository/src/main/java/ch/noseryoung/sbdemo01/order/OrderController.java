package ch.noseryoung.sbdemo01.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String getAllOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        List<OrderEntity> orders = orderService.getAllOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (!(i == orders.size() - 2)) {stringBuilder.append(", ");}
            stringBuilder.append(orders.get(i).toString());
        }
        stringBuilder.append("]");
        return String.valueOf(stringBuilder);
    }
    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") int orderId) {
        return orderService.getOrder(orderId).toString();
    }

    @PostMapping("/{id}")
    public String postOrder(
            @PathVariable("id") int orderId,
            @RequestParam("shippingMethodId") int shippingMethodId,
            @RequestParam("clientId") int clientId) {
        if (!orderService.postOrder(orderId, shippingMethodId, clientId)) {return "error";}
        return "done";
    }

    @PutMapping("/{id}")
    public String putOrder(
            @PathVariable("id") int orderId,
            @RequestParam("shippingMethodId") int shippingMethodId,
            @RequestParam("clientId") int clientId
    ) {
        if (!orderService.putOrder(orderId, shippingMethodId, clientId)) {return "error";}
        return "done";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") int orderId) {
        if (!orderService.deleteOrder(orderId)) {return "error";}
        return "done";
    }
}
