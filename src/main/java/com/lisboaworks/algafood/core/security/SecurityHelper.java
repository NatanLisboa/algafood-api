package com.lisboaworks.algafood.core.security;

import com.lisboaworks.algafood.domain.repository.OrderRepository;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class SecurityHelper {

    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) this.getAuthentication().getPrincipal();

        return jwt.getClaim("user_id");
    }

    public boolean manageRestaurant(Long restaurantId) {
        if (Objects.isNull(restaurantId)) {
            return false;
        }

        return restaurantRepository.existsResponsibleUser(restaurantId, this.getUserId());
    }

    public boolean manageOrderRestaurant(String orderCode) {
        return orderRepository.isOrderManagedByUser(orderCode, this.getUserId());
    }

    public boolean isAuthenticatedUserEquals(Long userId) {
        return Objects.nonNull(this.getUserId()) && Objects.nonNull(userId)
                && this.getUserId().equals(userId);
    }

    public boolean hasAuthority(String authorityName) {
        return this.getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean canManageOrder(String orderCode) {
        return this.hasAuthority("SCOPE_WRITE") && (this.hasAuthority("MANAGE_ORDERS") ||
                this.manageOrderRestaurant(orderCode));
    }

}
