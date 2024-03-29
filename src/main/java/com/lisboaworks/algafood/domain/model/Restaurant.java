package com.lisboaworks.algafood.domain.model;

import com.lisboaworks.algafood.core.validation.FreeShippingFeeDescriptiveName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FreeShippingFeeDescriptiveName(
        valueField = "shippingFee",
        descriptionField = "name",
        mandatoryDescription = "Free shipping"
)
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal shippingFee;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cuisine cuisine;

    @Embedded
    private Address address;

    private Boolean active = Boolean.TRUE;

    private Boolean open = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime registerDatetime;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime lastUpdateDatetime;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "restaurant_responsible_user",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> responsibleUsers = new HashSet<>();

    public void activate() {
        this.setActive(true);
    }

    public void inactivate() {
        this.setActive(false);
    }

    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return this.getPaymentMethods().remove(paymentMethod);
    }

    public boolean addPaymentMethod(PaymentMethod paymentMethod) {
        return this.getPaymentMethods().add(paymentMethod);
    }

    public void open() {
        this.setOpen(true);
    }

    public void close() {
        this.setOpen(false);
    }

    public void addResponsibleUser(User user) {
        this.responsibleUsers.add(user);
    }

    public void removeResponsibleUser(User user) {
        this.responsibleUsers.remove(user);
    }

    public boolean hasPaymentMethod(PaymentMethod paymentMethod) {
        return this.paymentMethods.contains(paymentMethod);
    }

    public boolean hasProduct(Product product) {
        return this.products.contains(product);
    }

    public Boolean isActive() {
        return this.active;
    }

    public Boolean isInactive() {
        return !this.isActive();
    }

    public Boolean isOpen() {
        return this.open;
    }

    public Boolean isClosed() {
        return !this.isOpen();
    }

    public Boolean canBeActivated() {
        return this.isInactive();
    }

    public Boolean canBeInactivated() {
        return this.isActive();
    }

    public Boolean canBeOpened() {
        return this.isActive() && this.isClosed();
    }

    public Boolean canBeClosed() {
        return this.isOpen();
    }

}
