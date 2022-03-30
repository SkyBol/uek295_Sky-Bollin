package ch.noseryoung.sbdemo01.order;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@Log4j2
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {return orderService.getAllOrders();}
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") int orderId) {return orderService.getOrder(orderId);}
    @PostMapping("/")
    public ResponseEntity<Order> postOrder(@RequestBody @Valid Order newOrder) {return orderService.postOrder(newOrder);}
    @PutMapping("/{id}")
    public ResponseEntity<Order> putOrder(@RequestBody @Valid Order updatableOrder, @PathVariable("id") int orderId) {return orderService.putOrder(updatableOrder, orderId);}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") int orderId) {return orderService.deleteOrder(orderId);}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandler(RuntimeException re) {
        log.warn("RuntimeException:" + re.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
    }
}
