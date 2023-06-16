package com.lisboaworks.algafood.domain.model;

import com.lisboaworks.algafood.core.validation.FreeShippingFeeDescriptiveName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    public void activate() {
        this.setActive(true);
    }

    public void inactivate() {
        this.setActive(false);
    }
}
