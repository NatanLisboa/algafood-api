package com.lisboaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lisboaworks.algafood.ValidationGroups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonRootName("cuisine")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuisine {

    @NotNull(groups = ValidationGroups.CuisineId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "cuisine")
    private List<Restaurant> restaurants = new ArrayList<>();

}
