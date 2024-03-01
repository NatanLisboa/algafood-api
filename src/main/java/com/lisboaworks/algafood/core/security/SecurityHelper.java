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

    public boolean isAuthenticated() {
        return this.getAuthentication().isAuthenticated();
    }

    public boolean hasScopeRead() {
        return hasAuthority("SCOPE_READ");
    }

    public boolean hasScopeWrite() {
        return hasAuthority("SCOPE_WRITE");
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) this.getAuthentication().getPrincipal();

        return Long.parseLong(jwt.getClaim("user_id"));
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
        return hasAuthority("SCOPE_WRITE") && (hasAuthority("MANAGE_ORDERS") ||
                manageOrderRestaurant(orderCode));
    }

    public boolean canGetPaymentMethods() {
        return hasScopeRead() && isAuthenticated();
    }

    public boolean canGetRestaurants() {
        return hasScopeRead() && isAuthenticated();
    }

    public boolean canGetCuisines() {
        return hasScopeRead() && isAuthenticated();
    }

    public boolean canEditCuisines() {
        return hasScopeWrite() && hasAuthority("EDIT_CUISINES");
    }

    public boolean canManageRestaurantsRegister() {
        return hasScopeWrite() && hasAuthority("EDIT_RESTAURANTS");
    }

    public boolean canManageRestaurantOperation(Long restaurantId) {
        return hasScopeWrite() && (hasAuthority("EDIT_RESTAURANTS") || manageRestaurant(restaurantId));
    }

    public boolean canGetAllOrders(Long customerId, Long restaurantId) {
        return hasScopeRead() && (hasAuthority("GET_ORDERS") ||
                isAuthenticatedUserEquals(customerId) ||
                manageRestaurant(restaurantId));
    }

    public boolean canGetAllOrders() {
        return isAuthenticated() && hasScopeRead();
    }

    public boolean canGetOrdersById() {
        return hasScopeRead() && isAuthenticated();
    }

    public boolean canCreateOrders() {
        return hasScopeWrite() && isAuthenticated();
    }

    public boolean canEditPaymentMethods() {
        return hasScopeWrite() && hasAuthority("EDIT_PAYMENT_METHODS");
    }

    public boolean canGetCities() {
        return hasScopeRead() && isAuthenticated();
    }

    public boolean canEditCities() {
        return hasScopeWrite() && hasAuthority("EDIT_CITIES");
    }

    public boolean canGetStates() {
        return hasScopeRead() && isAuthenticated();
    }

    public boolean canEditStates() {
        return hasScopeWrite() && hasAuthority("EDIT_STATES");
    }

    public boolean canGetUsersUserGroupsAndPermissions() {
        return hasScopeRead() && hasAuthority("GET_USERS_USER_GROUPS_PERMISSIONS");
    }

    public boolean canEditUsersUserGroupsAndPermissions() {
        return hasScopeWrite() && hasAuthority("EDIT_USERS_USER_GROUPS_PERMISSIONS");
    }

    public boolean canChangeOwnPassword(Long userId) {
        return hasScopeWrite() && isAuthenticatedUserEquals(userId);
    }

    public boolean canModifyUserData(Long userId) {
        return hasScopeWrite() && (hasAuthority("EDIT_USERS_USER_GROUPS_PERMISSIONS") ||
                        isAuthenticatedUserEquals(userId));
    }

    public boolean canGetStatistics() {
        return hasScopeRead() && hasAuthority("GENERATE_REPORTS");
    }

}

