package ch.noseryoung.sbdemo01.order;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPTION_VALUE")
    @Column(name="order_id")
    private Long id;

    @Column(name="shippingmethod_id")
    private Long shippingMethodId;

    @Column(name="client_id")
    private Long clientId;

    public Order(int shippingMethodId, int clientId) {
        this.shippingMethodId = (long)shippingMethodId;
        this.clientId = (long)clientId;
    }
    public Order(int id, int shippingMethodId, int clientId) {
        this.id = (long)id;
        this.shippingMethodId = (long)shippingMethodId;
        this.clientId = (long)clientId;
    }

    public long getId() {return id;}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shippingMethod_id=" + shippingMethodId +
                ", client_id=" + clientId +
                '}';
    }
}
