package ch.noseryoung.sbdemo01.order;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @NoArgsConstructor
@Table(name="order")
public class OrderEntity {
    @Id
    @Column(name="order_id")
    private Long id;

    @Column(name="shippingmethod_id")
    private Long shippingMethod_id;

    @Column(name="client_id")
    private Long client_id;

    public OrderEntity(Long id, Long shippingMethod_id, Long client_id) {
        this.id = id;
        this.shippingMethod_id = shippingMethod_id;
        this.client_id = client_id;
    }
    public OrderEntity(int order_id, int shippingMethod_id, int client_id) {
        this.id = (long)order_id;
        this.shippingMethod_id = (long)shippingMethod_id;
        this.client_id = (long)client_id;
    }

    public long getId() {return id;}

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", shippingMethod_id=" + shippingMethod_id +
                ", client_id=" + client_id +
                '}';
    }
}
