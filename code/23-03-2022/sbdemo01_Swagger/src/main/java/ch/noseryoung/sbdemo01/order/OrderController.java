package ch.noseryoung.sbdemo01.order;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getAllOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        List<OrderEntity> orders = orderService.getAllOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (!(i == orders.size() - 2)) {stringBuilder.append(", ");}
            if (orders.get(i) != null) {stringBuilder.append(orders.get(i).toString());}
        }
        stringBuilder.append("]");
        return String.valueOf(stringBuilder);
    }
    @ApiOperation(value="Returns the Order with the given ID")
    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") int orderId) {
        OrderEntity order = orderService.getOrder(orderId);
        if (order != null) {return order.toString();}
        return null;
    }

    @ApiOperation(value="Creates a Order with the given ID, shippingMethod-ID and client-ID")
    @PostMapping("/{id}")
    public String postOrder(@PathVariable("id") int orderId, @RequestParam("shippingMethodId") int shippingMethodId, @RequestParam("clientId") int clientId) {
        if (!orderService.postOrder(orderId, shippingMethodId, clientId)) {return "error";}
        return "done";
    }

    @ApiOperation(value="Updates the Orders shippingMethod-ID and client-ID with the given ID")
    @PutMapping("/{id}")
    public String putOrder(@PathVariable("id") int orderId, @RequestParam("shippingMethodId") int shippingMethodId, @RequestParam("clientId") int clientId) {
        if (!orderService.putOrder(orderId, shippingMethodId, clientId)) {return "error";}
        return "done";
    }

    @ApiOperation(value="Deletes the Order with the given ID")
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") int orderId) {
        if (!orderService.deleteOrder(orderId)) {return "error";}
        return "done";
    }
}
