package ch.noseryoung.sbdemo01.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {return orderService.getAllOrders();}
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") int orderId) {return orderService.getOrder(orderId);}
    @PostMapping("/")
    public ResponseEntity<Order> postOrder(@RequestBody Order newOrder) {return orderService.postOrder(newOrder);}
    @PutMapping("/{id}")
    public ResponseEntity<Order> putOrder(@RequestBody Order updatableOrder) {return orderService.putOrder(updatableOrder);}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") int orderId) {return orderService.deleteOrder(orderId);}
}
