package com.lisboaworks.algafood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELLED("Cancelled", CREATED);

    OrderStatus(String description, OrderStatus... previousStatuses) {
        this.description = description;
        this.previousStatuses = Arrays.asList(previousStatuses);
    }

    private String description;
    private List<OrderStatus> previousStatuses;

    public boolean canBeChangedTo(OrderStatus newStatus) {
        return newStatus.previousStatuses.contains(this);
    }

    public String getFormattedPreviousStatuses() {
        return IntStream.range(0, this.getPreviousStatuses().size())
                .mapToObj(i -> {
                    StringBuilder previousStatuses = new StringBuilder();
                    if (i < this.getPreviousStatuses().size() - 1) {
                        previousStatuses.append(this.getPreviousStatuses().get(i).getDescription()).append(", ");
                    } else {
                        previousStatuses.append(this.getPreviousStatuses().get(i).getDescription());
                    }
                    return previousStatuses.toString();
                }).collect(Collectors.joining());
    }

}
