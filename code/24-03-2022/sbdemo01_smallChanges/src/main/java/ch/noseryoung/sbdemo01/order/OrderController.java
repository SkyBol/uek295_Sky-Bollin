package ch.noseryoung.sbdemo01.order;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@EnableSwagger2
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value="Returns all Orders")
    @GetMapping("/")
    public ResponseEntity<String> getAllOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        List<Order> orders = orderService.getAllOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (i != orders.size() - 2) {stringBuilder.append(", ");}
            if (orders.get(i) != null) {stringBuilder.append(orders.get(i).toString());}
        }
        stringBuilder.append("]");
        return ResponseEntity.ok(stringBuilder.toString());
    }
    @ApiOperation(value="Returns the Order with the given ID")
    @GetMapping("/{id}")
    public ResponseEntity<String> getOrder(@PathVariable("id") int orderId) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {return ResponseEntity.ok(order.toString());}
        return ResponseEntity.status(404).body("error");
    }
    @ApiOperation(value="Creates a Order with the given ID, shippingMethod-ID and client-ID")
    @PostMapping("/")
    public ResponseEntity<Order> postOrder(@RequestParam("shippingMethodId") int shippingMethodId, @RequestParam("clientId") int clientId) {
        Order createdOrder = orderService.postOrder(shippingMethodId, clientId);
        if (createdOrder == null) {return ResponseEntity.status(400).body(null);}
        return ResponseEntity.ok(createdOrder);
    }
    @ApiOperation(value="Updates the Orders shippingMethod-ID and client-ID with the given ID")
    @PutMapping("/{id}")
    public ResponseEntity<Order> putOrder(@PathVariable("id") int orderId, @RequestParam("shippingMethodId") int shippingMethodId, @RequestParam("clientId") int clientId) {
        Order updatedOrder = orderService.putOrder(orderId, shippingMethodId, clientId);
        if (updatedOrder == null) {return ResponseEntity.status(400).body(null);}
        return ResponseEntity.ok(updatedOrder);
    }
    @ApiOperation(value="Deletes the Order with the given ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") int orderId) {
        if (!orderService.deleteOrder(orderId)) {return ResponseEntity.status(400).body("error");}
        return ResponseEntity.ok("done");
    }
}
