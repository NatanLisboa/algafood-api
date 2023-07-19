package com.lisboaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductPhoto {

    @Id
    @Column(name = "product_id")
    @EqualsAndHashCode.Include
    private Long id;

    private String filename;

    private String description;

    private String contentType;

    private Long size;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId //Determine that this entity has the same id of its owner (i.e., ProductPhoto also has product_id as its id, as Product)
    private Product product;


    public Long getRestaurantId() {
        if (Objects.nonNull(this.product)) {
            return this.product.getRestaurant().getId();
        }
        return null;
    }
}
