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

        @PreAuthorize("@securityHelper.canGetOrdersById()")
        @PostAuthorize("hasAuthority('GET_ORDERS') or " +
        "@securityHelper.isAuthenticatedUserEquals(returnObject.customer.id) or " +
        "@securityHelper.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGetById { }

        @PreAuthorize("@securityHelper.canCreateOrders()")
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

        @PreAuthorize("@securityHelper.canEditPaymentMethods()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface Cities {

        @PreAuthorize("@securityHelper.canGetCities()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("@securityHelper.canEditCities()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface States {

        @PreAuthorize("@securityHelper.canGetStates()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("@securityHelper.canEditStates()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface UsersUserGroupsPermissions {

        @PreAuthorize("@securityHelper.canGetUsersUserGroupsAndPermissions()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

        @PreAuthorize("@securityHelper.canEditUsersUserGroupsAndPermissions()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

        @PreAuthorize("@securityHelper.canChangeOwnPassword(#userId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanChangeOwnPassword { }

        @PreAuthorize("@securityHelper.canModifyUserData(#userId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanModifyUserData { }

    }

    @interface Statistics {

        @PreAuthorize("@securityHelper.canGetStatistics()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanGet { }

    }

}
