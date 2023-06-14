package com.lisboaworks.algafood.domain.exception;

public class UserGroupNotFoundException extends EntityNotFoundException {

    public UserGroupNotFoundException(String message) {
        super(message);
    }

    public UserGroupNotFoundException(Long userGroupId) {
        this(String.format("There is no user group with id %d in database", userGroupId));
    }
}
