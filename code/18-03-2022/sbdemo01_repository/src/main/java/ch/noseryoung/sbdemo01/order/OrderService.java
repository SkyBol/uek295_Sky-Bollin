package ch.noseryoung.sbdemo01.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orders;

    public List<OrderEntity> getAllOrders() {
        return orders.findAll();
    }
    public OrderEntity getOrder(int order_id) {
        return orders.getById((long)order_id);
    }
    public boolean postOrder(String order_id, String shippingMethod_id, String client_id) {
        try {
            OrderEntity newOrder = new OrderEntity(Long.parseLong(order_id), Long.parseLong(shippingMethod_id), Long.parseLong(client_id));
            orders.save(newOrder);
        } catch (Exception e) {return false;}
        return true;
    }
    public boolean deleteOrder(String order_idRAW) {
        try {
            Long order_id = Long.parseLong(order_idRAW);
            orders.deleteById(order_id);
        } catch (Exception e) {return false;}
        return true;
    }
}
