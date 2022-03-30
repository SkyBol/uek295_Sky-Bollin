package ch.noseryoung.sbdemo01.order;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public ResponseEntity<List<Order>> getAllOrders() {
        log.info("Returned all Orders");
        return ResponseEntity.ok(orderRepo.findAll());
    }
    public ResponseEntity<Order> getOrder(int orderId) {
        try {
            Order foundOrder = orderRepo.getById((long) orderId);
            log.info("Returned found Order");
            return ResponseEntity.ok(foundOrder);
        } catch (Exception e) {return ResponseEntity.status(404).body(null);}
    }
    public ResponseEntity<Order> postOrder(Order newOrder) {
        try {
            orderRepo.save(newOrder);
            log.info("Order created");
            return ResponseEntity.ok(newOrder);
        } catch (Exception e) {return ResponseEntity.status(400).body(null);}
    }
    public ResponseEntity<Order> putOrder(Order updatableOrder) {
        if (getOrder((int) updatableOrder.getId()) == null) {return ResponseEntity.status(400).body(null);}
        orderRepo.save(updatableOrder);
        log.info("Updated Order");
        return ResponseEntity.ok(updatableOrder);
    }
    public ResponseEntity<String> deleteOrder(int orderId) {
        try {
            orderRepo.deleteById((long) orderId);
            return ResponseEntity.ok("done");
        } catch (Exception e) {return ResponseEntity.status(400).body("error");}
    }
}
