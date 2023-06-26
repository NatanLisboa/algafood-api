package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.UserGroupNotFoundException;
import com.lisboaworks.algafood.domain.model.Permission;
import com.lisboaworks.algafood.domain.model.UserGroup;
import com.lisboaworks.algafood.domain.repository.UserGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserGroupRegisterService {

    private static final String USER_GROUP_ALREADY_IN_USE_MESSAGE = "User group with id %d cannot be deleted because it is already being used by other entities in database";
    private final UserGroupRepository userGroupRepository;
    private final PermissionRegisterService permissionRegisterService;

    public UserGroup findOrThrowException(Long userGroupId) {
        return userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new UserGroupNotFoundException(userGroupId));
    }

    @Transactional
    public UserGroup save(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    @Transactional
    public void delete(Long userGroupId) {

        try {
            userGroupRepository.deleteById(userGroupId);
            userGroupRepository.flush(); // execute the transactions made so far to the database
        } catch (EmptyResultDataAccessException e) {
            throw new UserGroupNotFoundException(userGroupId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(USER_GROUP_ALREADY_IN_USE_MESSAGE, userGroupId));
        }

    }

    @Transactional
    public void associatePermission(Long userGroupId, Long permissionId) {
        UserGroup userGroup = this.findOrThrowException(userGroupId);
        Permission permission = permissionRegisterService.findOrThrowException(permissionId);

        userGroup.addPermission(permission);
    }

    @Transactional
    public void disassociatePermission(Long userGroupId, Long permissionId) {
        UserGroup userGroup = this.findOrThrowException(userGroupId);
        Permission permission = permissionRegisterService.findOrThrowException(permissionId);

        userGroup.removePermission(permission);
    }
}
