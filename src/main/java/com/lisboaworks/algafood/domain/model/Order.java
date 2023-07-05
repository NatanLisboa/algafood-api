package com.lisboaworks.algafood.domain.model;

import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "`order`")
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
    private OffsetDateTime creationDatetime;

    private OffsetDateTime confirmationDatetime;

    private OffsetDateTime cancellationDatetime;

    private OffsetDateTime deliveryDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;


    public BigDecimal calculateTotalValue() {
        return this.calculateSubtotal().add(this.shippingFee);
    }

    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItem item : items) {
            subtotal = subtotal.add(item.getTotalPrice());
        }
        return subtotal;
    }

    public void confirm() {
        this.setStatus(OrderStatus.CONFIRMED);
        this.setConfirmationDatetime(OffsetDateTime.now());
    }

    public void cancel() {
        this.setStatus(OrderStatus.CANCELLED);
        this.setCancellationDatetime(OffsetDateTime.now());
    }

    public void deliver() {
        this.setStatus(OrderStatus.DELIVERED);
        this.setDeliveryDatetime(OffsetDateTime.now());
    }

    private void setStatus(OrderStatus newStatus) {
        if (!status.canBeChangedTo(newStatus)) {
            throw new BusinessRuleException(String.format("Status from order number %d cannot be changed from '%s' to '%s'. " +
                            "Only from '%s' to '%s' is accepted.",
                    id,
                    status.getDescription(),
                    newStatus.getDescription(),
                    newStatus.getFormattedPreviousStatuses(),
                    newStatus.getDescription()));
        }
        this.status = newStatus;
    }

}
