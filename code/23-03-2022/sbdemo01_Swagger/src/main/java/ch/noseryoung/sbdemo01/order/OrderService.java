package ch.noseryoung.sbdemo01.order;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OrderService {
    @Autowired
    private OrderRepo orders;

    public List<OrderEntity> getAllOrders() {return orders.findAll();}
    public OrderEntity getOrder(int order_id) {
        try {return orders.getById((long) order_id);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return null;
        }
    }
    public boolean postOrder(int order_id, int shippingMethod_id, int client_id) {
        try {
            OrderEntity newOrder = new OrderEntity(order_id, shippingMethod_id, client_id);
            orders.save(newOrder);
            return true;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
    public boolean putOrder(int order_id, int shippingMethod_id, int client_id) {
        try {return getOrder(order_id) != null && postOrder(order_id, shippingMethod_id, client_id);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
    public boolean deleteOrder(int order_id) {
        try {
            orders.deleteById((long)order_id);
            return true;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
}
