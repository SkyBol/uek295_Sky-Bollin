package ch.noseryoung.sbdemo01.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @NotNull
    @Column(name="shippingmethod_id")
    private Long shippingMethodId;

    @NotNull
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

    public void setID(int orderID) {this.id = (long)orderID;}
}
