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

    public List<Order> getAllOrders() {return orders.findAll();}
    public Order getOrder(int orderId) {
        try {return orders.getById((long) orderId);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return null;
        }
    }
    public Order postOrder(int shippingMethodId, int clientId) {
        try {
            Order newOrder = new Order(shippingMethodId, clientId);
            orders.save(newOrder);
            return newOrder;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return null;
        }
    }
    public Order putOrder(int orderId, int shippingMethodId, int clientId) {
        try {
            if (getOrder(orderId) != null) {
                Order createdOrder = new Order(orderId, shippingMethodId, clientId);
                orders.save(createdOrder);
                return createdOrder;
            }
        } catch (Exception e) {log.error(e.getStackTrace());}
        return null;
    }
    public boolean deleteOrder(int orderId) {
        try {
            orders.deleteById((long)orderId);
            return true;
        } catch (Exception e) {
            log.error(e.getStackTrace());
            return false;
        }
    }
}
