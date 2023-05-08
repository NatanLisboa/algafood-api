package com.lisboaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal shippingFee;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @CreationTimestamp
    private LocalDateTime creationDatetime;

    private LocalDateTime confirmationDatetime;

    private LocalDateTime cancellationDatetime;

    private LocalDateTime deliveryDatetime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User customer;

    @Embedded
    private Address deliveryAddress;

    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;


}
