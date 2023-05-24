package com.lisboaworks.algafood.domain.model;

import com.lisboaworks.algafood.ValidationGroups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Valid
    @ConvertGroup(to = ValidationGroups.StateId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private State state;

}
