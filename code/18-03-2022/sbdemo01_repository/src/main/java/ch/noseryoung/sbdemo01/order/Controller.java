package ch.noseryoung.sbdemo01.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class Controller {
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
    public String getOrder(@PathVariable("id") int order_id) {
        return orderService.getOrder(order_id).toString();
    }

    //TODO: Make Put not create
    @PutMapping("/{id}")
    @PostMapping("/{id}")
    public String postAndPutOrder(
            @PathVariable("id") String order_id,
            @RequestParam("shippingMethod_id") String shippingMethod_id,
            @RequestParam("client_id") String client_id) {
        if (!orderService.postOrder(order_id, shippingMethod_id, client_id)) {return "error";}
        return "done";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") String order_id) {
        if (!orderService.deleteOrder(order_id)) {return "error";}
        return "done";
    }
}
