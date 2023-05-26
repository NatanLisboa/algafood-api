package com.lisboaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lisboaworks.algafood.core.validation.FreeShippingFeeDescriptiveName;
import com.lisboaworks.algafood.core.validation.ValidationGroups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal shippingFee;

    @Valid
    @ConvertGroup(to = ValidationGroups.CuisineId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cuisine cuisine;

    @JsonIgnore
    @Embedded
    private Address address;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime registerDatetime;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime lastUpdateDatetime;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

}
