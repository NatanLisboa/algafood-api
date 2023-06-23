package com.lisboaworks.algafood.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException {
    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long permissionId) {
        this(String.format("There is no permission registered with code %d", permissionId));
    }
}
