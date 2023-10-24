package com.lisboaworks.algafood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    @interface Cuisines {

        @PreAuthorize("@securityHelper.canGetCuisines()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("@securityHelper.canEditCuisines()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface Restaurants {

        @PreAuthorize("@securityHelper.canGetRestaurants()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("@securityHelper.canManageRestaurantsRegister()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanManageRegister { }

        @PreAuthorize("@securityHelper.canManageRestaurantOperation(#restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanManageOperation { }

    }

    @interface Orders {

        @PreAuthorize("@securityHelper.canGetAllOrders(#filter.customerId,#filter.restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGetAll { }

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('GET_ORDERS') or " +
        "@securityHelper.isAuthenticatedUserEquals(returnObject.customer.id) or " +
        "@securityHelper.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGetById { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanCreate { }

        @PreAuthorize("@securityHelper.canManageOrder(#orderCode)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanManage { }

    }

    @interface PaymentMethods {

        @PreAuthorize("@securityHelper.canGetPaymentMethods()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_PAYMENT_METHODS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface Cities {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_CITIES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface States {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_STATES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface UsersUserGroupsPermissions {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GET_USERS_USER_GROUPS_PERMISSIONS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_USERS_USER_GROUPS_PERMISSIONS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and @securityHelper.isAuthenticatedUserEquals(#userId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanChangeOwnPassword { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USERS_USER_GROUPS_PERMISSIONS') or " +
                "@securityHelper.isAuthenticatedUserEquals(#userId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanModifyUserData { }

    }

    @interface Statistics {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GENERATE_REPORTS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

    }

}
